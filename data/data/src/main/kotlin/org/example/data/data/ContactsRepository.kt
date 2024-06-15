package org.example.data.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.data.database.model.ContactEntity

abstract class ContactsRepository {
    abstract val contacts: Flow<PagingData<ContactEntity>>
}