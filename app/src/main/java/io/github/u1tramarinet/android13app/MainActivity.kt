package io.github.u1tramarinet.android13app

import android.media.RingtoneManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.u1tramarinet.android13app.ui.queryFontFamilyAttrResId
import io.github.u1tramarinet.android13app.ui.queryFontFamilyName
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamilyResId = this.queryFontFamilyAttrResId()
        val fontFamilyName = this.queryFontFamilyName()
        Log.d("MainActivity", "fontFamilyResId: $fontFamilyResId, fontFamilyName: $fontFamilyName")

        val file = File("/")
        Log.d("MainActivity", "file=$file exists=${file.exists()}")
        // file=/

        val file1 = Environment.getExternalStorageDirectory()
        Log.d("MainActivity", "file=$file1 exists=${file1.exists()}")
        // file=/storage/emulated/10

        val file2 = File("/product/")
        Log.d("MainActivity", "file=$file2 exists=${file2.exists()}")
        // file=/product

        setContent {
            Android13AppTheme {
                Android13App()
            }
        }
        playNotification()
    }

    private fun playNotification() {
        Log.d("MainActivity", "playNotification")
        val n = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(this, n)
        r.play()
    }
}