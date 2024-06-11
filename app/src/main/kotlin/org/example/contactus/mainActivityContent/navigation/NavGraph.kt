package org.example.contactus.mainActivityContent.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.createGraph

@Composable
internal fun NavController.navGraph(
    startDestination: String = "bob",
): NavGraph = rememberNavGraph(startDestination = startDestination) {
    composable("bob") {
        Text("bob")
    }
}

@Composable
private fun NavController.rememberNavGraph(
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit,
) = remember(startDestination, builder) {
    createGraph(startDestination, null, builder)
}