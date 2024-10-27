package io.github.u1tramarinet.android13app.ui.screen.nested

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedScreen(
    modifier: Modifier = Modifier,
    uiAction: NestedScreenUiAction,
    startDestination: Android13AppNestedRoute = Android13AppNestedRoute.Nested1,
    additionalDestination: Android13AppNestedRoute? = null,
) {
    val navController: NavHostController = rememberAnimatedNavController()
    val innerUiAction = NestedScreenUiAction(
        onBackClick = {
            if (navController.previousBackStackEntry == null) {
                uiAction.onBackClick.invoke()
            } else {
                navController.popBackStack()
            }
        }
    )
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(Android13AppNestedRoute.Nested1.route) {
            NestedScreenContent(
                route = Android13AppNestedRoute.Nested1,
                uiAction = innerUiAction,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(Android13AppNestedRoute.Nested2.route) {
            NestedScreenContent(
                route = Android13AppNestedRoute.Nested2,
                uiAction = innerUiAction,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(Android13AppNestedRoute.Nested3.route) {
            NestedScreenContent(
                route = Android13AppNestedRoute.Nested3,
                uiAction = innerUiAction,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(Android13AppNestedRoute.Nested4.route) {
            NestedScreenContent(
                route = Android13AppNestedRoute.Nested4,
                uiAction = innerUiAction,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    }
    LaunchedEffect(key1 = additionalDestination) {
        if (additionalDestination != null && additionalDestination != startDestination) {
            navController.navigate(additionalDestination.route)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NestedScreenContent(
    modifier: Modifier = Modifier,
    route: Android13AppNestedRoute,
    uiAction: NestedScreenUiAction,
    onItemClick: (route: Android13AppNestedRoute) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Nested Screen ${route.suffix}") },
                navigationIcon = {
                    IconButton(onClick = uiAction.onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Android13AppNestedRoute.list().forEach {
                Button(
                    onClick = { onItemClick(it) },
                    enabled = it != route,
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text("Go to ${it.route}")
                }
            }
        }
    }
}

sealed class Android13AppNestedRoute(
    val suffix: String,
    val route: String = "nested$suffix",
) {
    object Nested1 : Android13AppNestedRoute(suffix = "1")

    object Nested2 : Android13AppNestedRoute(suffix = "2")

    object Nested3 : Android13AppNestedRoute(suffix = "3")

    object Nested4 : Android13AppNestedRoute(suffix = "4")

    companion object {
        fun list() = listOf(Nested1, Nested2, Nested3, Nested4)

        fun from(path: String?): Android13AppNestedRoute {
            return list().firstOrNull {
                it.route == path
            } ?: Nested1
        }
    }
}