package org.example.feature.contactList

import org.example.feature.contactList.contract.ContactListActions
import org.example.feature.contactList.contract.ContactListEvents
import org.example.feature.contactList.contract.ContactListState
import org.example.utils.mvi.MVI

public class ContactListViewModel :
    MVI<ContactListState, ContactListEvents, ContactListActions>(ContactListState()) {
    override fun process(event: ContactListEvents) {

    }

}
