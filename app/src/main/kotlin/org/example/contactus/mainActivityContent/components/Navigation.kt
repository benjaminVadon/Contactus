package org.example.contactus.mainActivityContent.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.example.feature.contactDetail.ContactDetailScreen
import org.example.feature.contactDetail.contactDetail
import org.example.feature.contactList.ContactListNavigation
import org.example.feature.contactList.ContactListScreen
import org.example.feature.contactList.contactList

@Composable
internal fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = ContactListScreen
) {
    contactList(
        ContactListNavigation(
            goToContactDetails = { navController.navigate(ContactDetailScreen(it)) }
        )
    )
    contactDetail()
}