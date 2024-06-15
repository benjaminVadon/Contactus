package org.example.data.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.data.data.mediator.ContactsMediator
import org.example.data.database.AppDatabase
import org.example.data.database.model.ContactEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ContactsRepositoryImpl @Inject constructor(
    contactsMediator: ContactsMediator,
    database: AppDatabase,
) : ContactsRepository() {
    private val contactDao = database.contactDao()
    private val pager: Pager<Int, ContactEntity> = Pager(
        config = PagingConfig(pageSize = 10),
        remoteMediator = contactsMediator,
    ) {
        contactDao.getAll()
    }

    override val contacts: Flow<PagingData<ContactEntity>>
        get() = pager.flow
}