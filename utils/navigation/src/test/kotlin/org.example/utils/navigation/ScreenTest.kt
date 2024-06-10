package org.example.utils.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import io.mockk.mockk
import org.example.utils.mvi.MVI
import org.example.utils.mvi.contract.Action
import org.example.utils.mvi.contract.Event
import org.example.utils.mvi.contract.State
import org.junit.Test

class ScreenTest {

    @Test
    fun `Screen initialization with all parameters`() {
        val route = "test_route"
        val arguments = listOf(mockk<NamedNavArgument>())
        val deepLinks = listOf(mockk<NavDeepLink>())
        val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
            { null }
        val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
            { null }
        val content: @Composable NavBackStackEntry.(ScreenNav) -> Unit = {}

        val screen = Screen<ScreenNav, ScreenVM>(
            route = route,
            arguments = arguments,
            deepLinks = deepLinks,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            content = content
        )

        assert(screen.route == route)
        assert(screen.arguments == arguments)
        assert(screen.deepLinks == deepLinks)
        assert(screen.enterTransition == enterTransition)
        assert(screen.exitTransition == exitTransition)
        assert(screen.popEnterTransition == enterTransition)
        assert(screen.popExitTransition == exitTransition)
        assert(screen.content == content)
    }

    @Test
    fun `Screen initialization with default parameters`() {
        val route = "test_route"
        val content: @Composable NavBackStackEntry.(ScreenNav) -> Unit = {}

        val screen = Screen<ScreenNav, ScreenVM>(
            route = route,
            content = content
        )

        assert(screen.route == route)
        assert(screen.arguments.isEmpty())
        assert(screen.deepLinks.isEmpty())
        assert(screen.enterTransition == null)
        assert(screen.exitTransition == null)
        assert(screen.content == content)
    }

    class ScreenVM : MVI<ScreenState, ScreenEvents, ScreenActions>(ScreenState()) {
        override fun process(event: ScreenEvents) {
        }
    }

    class ScreenState:State
    class ScreenEvents:Event
    class ScreenActions:Action
    class ScreenNav
}