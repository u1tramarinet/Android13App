package io.github.u1tramarinet.android13app.ui.screen.main

import io.github.u1tramarinet.android13app.Android13AppRoute

data class MainScreenUiAction(
    val onItemClick: (Android13AppRoute) -> Unit = {},
)