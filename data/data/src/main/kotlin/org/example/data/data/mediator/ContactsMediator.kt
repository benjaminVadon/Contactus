package org.example.data.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.APPEND
import androidx.paging.LoadType.PREPEND
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
import androidx.paging.RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import androidx.room.withTransaction
import kotlinx.datetime.Clock.System.now
import kotlinx.datetime.Instant
import org.example.data.data.mappers.ToContactEntity
import org.example.data.database.AppDatabase
import org.example.data.database.dao.ContactDao
import org.example.data.database.dao.LoadPageDao
import org.example.data.database.dao.LoadableApi.Contacts
import org.example.data.database.model.ContactEntity
import org.example.data.database.model.LoadPage
import org.example.data.network.api.ContactApi
import org.example.data.network.model.Contact
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

@OptIn(ExperimentalPagingApi::class)
class ContactsMediator @Inject constructor(
    private val contactApi: ContactApi,
    private val database: AppDatabase,
    private val toContactEntity: ToContactEntity,
) : RemoteMediator<Int, ContactEntity>() {

    private val contactDao: ContactDao = database.contactDao()
    private val loadPageDao: LoadPageDao = database.loadPageDao()

    override suspend fun initialize(): InitializeAction = if (haveToRefresh()) {
        LAUNCH_INITIAL_REFRESH
    } else {
        SKIP_INITIAL_REFRESH
    }

    private suspend fun haveToRefresh(): Boolean =
        when (val date = lastRetrievingDate()) {
            null -> true
            else -> now() - date >= 1.hours
        }

    private suspend fun lastRetrievingDate() =
        loadPageDao.getNextPage(Contacts)
            ?.lastUpdated
            ?.let(Instant.Companion::fromEpochMilliseconds)

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ContactEntity>
    ): MediatorResult = when (val page = loadType.getPage()) {
        null -> Success(endOfPaginationReached = true)
        else -> retrieveContacts(page = page, limit = state.config.pageSize, loadType = loadType)
    }

    private suspend fun LoadType.getPage(): Int? = when (this) {
        REFRESH -> MIN_PAGE
        PREPEND -> null
        APPEND -> getPageToLoad()
    }

    private suspend fun getPageToLoad(): Int? =
        when (val lastPage = loadPageDao.getNextPage(Contacts)) {
            null -> MIN_PAGE
            else -> lastPage.loadedPage?.takeIf { it <= MAX_PAGE }?.inc()
        }

    private suspend fun retrieveContacts(
        page: Int,
        limit: Int,
        loadType: LoadType
    ): MediatorResult = contactApi.getContacts(page = page, results = limit)
        .fold(
            onSuccess = {
                addContactsInDb(
                    contacts = it.results,
                    needReset = loadType == REFRESH,
                    loadedPage = page
                )
                Success(endOfPaginationReached = page >= MAX_PAGE)
            },
            onFailure = { Error(it) }
        )

    private suspend fun addContactsInDb(
        contacts: List<Contact>,
        needReset: Boolean,
        loadedPage: Int
    ) = database.withTransaction {
        if (needReset) {
            loadPageDao.delete(Contacts)
            contactDao.deleteAll()
        }
        loadPageDao.setNextPage(
            LoadPage(
                apiName = Contacts,
                loadedPage = loadedPage,
                lastUpdated = now().toEpochMilliseconds()
            )
        )
        contactDao.insertAll(contacts.map(toContactEntity::invoke))
    }

    companion object {
        private const val MIN_PAGE = 1
        private const val MAX_PAGE = 10000
    }
}