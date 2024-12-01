package io.github.u1tramarinet.android13app.ui.widget

import android.util.Log
import android.view.ViewConfiguration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

private const val TAG = "RepeatableButton"

@Composable
fun RepeatableButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: () -> Unit = onClick,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    var job: Job? = null
    val longPressTimeoutMs = remember { ViewConfiguration.getLongPressTimeout() }
    val keyRepeatTimeoutMs = remember { ViewConfiguration.getKeyRepeatTimeout() }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when(interaction) {
                is PressInteraction.Press -> {
                    job = scope.launch {
                        Log.d(TAG, "Wait for longPress")
                        delay(longPressTimeoutMs.toLong())
                        onLongClick()
                        Log.d(TAG, "onLongPress")
                        while (isActive) {
                            Log.d(TAG, "Wait for keyRepeat")
                            delay(keyRepeatTimeoutMs.toLong())
                            onLongClick()
                            Log.d(TAG, "onKeyRepeat")
                        }
                    }
                }
                is PressInteraction.Release -> {
                    Log.d(TAG, "onRelease")
                    job?.cancel()
                }
                is PressInteraction.Cancel -> {
                    Log.d(TAG, "cancel")
                    job?.cancel()
                }
            }
        }
    }
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}