package org.example.utils.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import org.example.utils.mvi.MVI
import org.example.utils.mvi.contract.Action
import org.example.utils.mvi.contract.Event
import org.example.utils.mvi.contract.State

private typealias TransitionScope = @JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>
private typealias EnterTransitionBuilder = TransitionScope.() -> EnterTransition?
private typealias ExitTransitionBuilder = TransitionScope.() -> ExitTransition?

open class Screen<T : Any, out VM>(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val deepLinks: List<NavDeepLink> = emptyList(),
    val enterTransition: EnterTransitionBuilder? = null,
    val exitTransition: ExitTransitionBuilder? = null,
    val popEnterTransition: EnterTransitionBuilder? = enterTransition,
    val popExitTransition: ExitTransitionBuilder? = exitTransition,
    val content: @Composable NavBackStackEntry.(T) -> Unit
) where VM : MVI<out State, out Event, out Action>