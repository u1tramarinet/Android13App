package io.github.u1tramarinet.android13app.ui.screen.sidenested

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SideNestedScreen(
    modifier: Modifier = Modifier,
    uiAction: SideNestedScreenUiAction,
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: Android13AppSideNestedRoute = Android13AppSideNestedRoute.SideNested1,
) {
    val innerUiAction = SideNestedScreenUiAction(
        onBackClick = {
            if (navController.previousBackStackEntry == null) {
                uiAction.onBackClick.invoke()
            } else {
                navController.popBackStack()
            }
        }
    )
    Row(modifier = modifier) {
        Column (modifier = Modifier.weight(0.3f).fillMaxHeight()) {
            Text(
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                text = "route=${navController.currentDestination}",
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = navController.currentDestination?.route != Android13AppSideNestedRoute.SideNested1.route,
                onClick = {
                    navController.navigate(Android13AppSideNestedRoute.SideNested1.route)
                },
            ) {
                Text(text = "SideNested1")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = navController.currentDestination?.route != Android13AppSideNestedRoute.SideNested2.route,
                onClick = {
                    navController.navigate(Android13AppSideNestedRoute.SideNested2.route)
                },
            ) {
                Text(text = "SideNested2")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = navController.currentDestination?.route != Android13AppSideNestedRoute.SideNested3.route,
                onClick = {
                    navController.navigate(Android13AppSideNestedRoute.SideNested3.route)
                },
            ) {
                Text(text = "SideNested3")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = navController.currentDestination?.route != Android13AppSideNestedRoute.SideNested4.route,
                onClick = {
                    navController.navigate(Android13AppSideNestedRoute.SideNested4.route)
                },
            ) {
                Text(text = "SideNested4")
            }
        }
        AnimatedNavHost(
            modifier = Modifier.weight(0.7f),
            navController = navController,
            startDestination = startDestination.route,
        ) {
            composable(Android13AppSideNestedRoute.SideNested1.route) {
                SideNestedScreenContent(
                    route = Android13AppSideNestedRoute.SideNested1,
                    uiAction = innerUiAction,
                    onItemClick = {
                        navController.navigate(it.route)
                    },
                    navController = navController,
                )
            }
            composable(Android13AppSideNestedRoute.SideNested2.route) {
                SideNestedScreenContent(
                    route = Android13AppSideNestedRoute.SideNested2,
                    uiAction = innerUiAction,
                    onItemClick = {
                        navController.navigate(it.route)
                    },
                    navController = navController,
                )
            }
            composable(Android13AppSideNestedRoute.SideNested3.route) {
                SideNestedScreenContent(
                    route = Android13AppSideNestedRoute.SideNested3,
                    uiAction = innerUiAction,
                    onItemClick = {
                        navController.navigate(it.route)
                    },
                    navController = navController,
                )
            }
            composable(Android13AppSideNestedRoute.SideNested4.route) {
                SideNestedScreenContent(
                    route = Android13AppSideNestedRoute.SideNested4,
                    uiAction = innerUiAction,
                    onItemClick = {
                        navController.navigate(it.route)
                    },
                    navController = navController,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SideNestedScreenContent(
    modifier: Modifier = Modifier,
    route: Android13AppSideNestedRoute,
    uiAction: SideNestedScreenUiAction,
    onItemClick: (route: Android13AppSideNestedRoute) -> Unit,
    navController: NavHostController,
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
            Text("route=${navController.currentDestination?.route}")
            Spacer(Modifier.height(16.dp))
            Android13AppSideNestedRoute.list().forEach {
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

sealed class Android13AppSideNestedRoute(
    val suffix: String,
    val route: String = "sideNested$suffix",
) {
    object SideNested1 : Android13AppSideNestedRoute(suffix = "1")

    object SideNested2 : Android13AppSideNestedRoute(suffix = "2")

    object SideNested3 : Android13AppSideNestedRoute(suffix = "3")

    object SideNested4 : Android13AppSideNestedRoute(suffix = "4")

    companion object {
        fun list() = listOf(SideNested1, SideNested2, SideNested3, SideNested4)

        fun from(path: String?): Android13AppSideNestedRoute {
            return list().firstOrNull {
                it.route == path
            } ?: SideNested1
        }
    }
}