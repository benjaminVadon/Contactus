package org.example.utils.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.example.utils.mvi.MVI
import org.example.utils.mvi.contract.Action
import org.example.utils.mvi.contract.Event
import org.example.utils.mvi.contract.State

fun <T : Any> NavGraphBuilder.composable(
    screen: Screen<T, MVI<out State, out Event, out Action>>,
    navigation: T
) = composable(
    route = screen.route,
    arguments = screen.arguments,
    deepLinks = screen.deepLinks,
    enterTransition = screen.enterTransition,
    exitTransition = screen.exitTransition,
    popEnterTransition = screen.popEnterTransition,
    popExitTransition = screen.popExitTransition,
    content = { screen.content(it, navigation) }
)