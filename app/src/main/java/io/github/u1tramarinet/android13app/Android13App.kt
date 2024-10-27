package io.github.u1tramarinet.android13app

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.github.u1tramarinet.android13app.ui.screen.main.MainScreen
import io.github.u1tramarinet.android13app.ui.screen.main.MainScreenUiAction
import io.github.u1tramarinet.android13app.ui.screen.nested.Android13AppNestedRoute
import io.github.u1tramarinet.android13app.ui.screen.nested.NestedScreen
import io.github.u1tramarinet.android13app.ui.screen.nested.NestedScreenUiAction
import io.github.u1tramarinet.android13app.ui.screen.notification.NotificationSampleScreen
import io.github.u1tramarinet.android13app.ui.screen.notification.NotificationSampleScreenUiAction
import io.github.u1tramarinet.android13app.ui.screen.widgetsample.WidgetSampleScreen
import io.github.u1tramarinet.android13app.ui.screen.widgetsample.WidgetSampleScreenUiAction

@Suppress("DEPRECATION")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Android13App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: Android13AppRoute = Android13AppRoute.Top,
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideInHorizontally(
                animationSpec = tween(300, easing = EaseIn),
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutHorizontally(
                animationSpec = tween(300, easing = EaseOut),
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideInHorizontally(
                animationSpec = tween(300, easing = EaseIn),
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutHorizontally(
                animationSpec = tween(300, easing = EaseOut),
            )
        }
    ) {
        composable(
            Android13AppRoute.Top.route,
            deepLinks = listOfNotNull(Android13AppRoute.Top.deepLink).map {
                navDeepLink { uriPattern = it }
            },
        ) {
            MainScreen(
                uiAction = MainScreenUiAction(
                    onItemClick = {
                        navController.navigate(it.route)
                    },
                )
            )
        }
        composable(
            Android13AppRoute.WidgetSample.route,
            deepLinks = listOfNotNull(Android13AppRoute.WidgetSample.deepLink).map {
                navDeepLink { uriPattern = it }
            },
        ) {
            WidgetSampleScreen(
                uiAction = WidgetSampleScreenUiAction(
                    onBackClick = {
                        navController.popBackStack()
                    }
                ),
            )
        }
        composable(
            Android13AppRoute.NotificationSample.route,
            deepLinks = listOfNotNull(Android13AppRoute.NotificationSample.deepLink).map {
                navDeepLink { uriPattern = it }
            },
        ) {
            NotificationSampleScreen(
                uiAction = NotificationSampleScreenUiAction(
                    onBackClick = {
                        navController.popBackStack()
                    }
                ),
            )
        }
        composable(
            Android13AppRoute.Nested.route,
            deepLinks = listOfNotNull(Android13AppRoute.Nested.deepLink).map {
                navDeepLink { uriPattern = it }
            },
        ) {
            NestedScreen(
                uiAction = NestedScreenUiAction(
                    onBackClick = {
                        navController.popBackStack()
                    }
                ),
            )
        }
        composable(
            Android13AppRoute.NestedWithArg().route,
            arguments = listOf(navArgument("destination") { type = NavType.StringType }),
            deepLinks = listOfNotNull(Android13AppRoute.NestedWithArg().deepLink).map {
                navDeepLink { uriPattern = it }
            },
        ) { backStackEntry ->
            val argDestination = backStackEntry.arguments?.getString("destination")
            Log.d("Android13App", "argDestination: $argDestination")
            val destination = Android13AppNestedRoute.from(argDestination)
            NestedScreen(
                additionalDestination = destination,
                uiAction = NestedScreenUiAction(
                    onBackClick = {
                        navController.popBackStack()
                    }
                ),
            )
        }
    }
}

sealed class Android13AppRoute(
    val route: String,
    val deepLink: String? = null,
) {
    object Top : Android13AppRoute("top")

    object WidgetSample : Android13AppRoute(
        "widget_sample",
        deepLink = "app://android13app/widget_sample",
    )

    object NotificationSample : Android13AppRoute(
        "notification_sample",
        deepLink = "app://android13app/notification_sample",
    )

    object Nested : Android13AppRoute(
        "nested",
        deepLink = "app://android13app/nested",
    )

    class NestedWithArg(destination: String = "{destination}") : Android13AppRoute(
        "nested/$destination",
        deepLink = "app://android13app/nested/$destination",
    )
}