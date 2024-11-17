package io.github.u1tramarinet.android13app.ui.widget

import android.content.Context
import android.media.AudioManager
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun SoundButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: RowScope.() -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick.withSoundEffects(LocalContext.current),
    ) {
        content()
    }
}

fun playSoundEffect(context: Context, force: Boolean = false) {
    if (force || isSoundEffectEnabled(context)) {
        (context.getSystemService(Context.AUDIO_SERVICE) as AudioManager)
            .playSoundEffect(AudioManager.FX_KEY_CLICK)
    }
}

fun (() -> Unit).withSoundEffects(context: Context, force: Boolean = false): () -> Unit = {
    playSoundEffect(context, force)
    this()
}

private fun isSoundEffectEnabled(context: Context): Boolean {
    val providerValue = Settings.System.getInt(
        context.contentResolver,
        Settings.System.SOUND_EFFECTS_ENABLED,
        0,
    )
    Log.d("Android13App", "isSoundEffectEnabled: $providerValue")
    return providerValue == 1
}