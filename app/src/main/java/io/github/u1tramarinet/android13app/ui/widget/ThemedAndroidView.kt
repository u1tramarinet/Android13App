package io.github.u1tramarinet.android13app.ui.widget

import android.content.Context
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import io.github.u1tramarinet.android13app.ui.queryThemeAttrResId
import io.github.u1tramarinet.android13app.ui.theme.LocalAndroidViewTheme

@Composable
@UiComposable
fun <T : View> ThemedAndroidView(
    modifier: Modifier = Modifier,
    factory: (context: Context) -> T,
    update: ((T) -> Unit) = NoOpUpdate
) {
    val defaultThemeResId = LocalAndroidViewTheme.current.themeResId
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val styleRes = context.queryThemeAttrResId() ?: defaultThemeResId
            val themedContext = ContextThemeWrapper(context, styleRes)
            factory(themedContext)
        },
        update = update,
    )
}