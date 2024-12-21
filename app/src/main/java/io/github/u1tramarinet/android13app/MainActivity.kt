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
        setContent {
            Android13AppTheme {
                Android13App()
            }
        }
        playNotification()
        testFontFamily()
        testStorage()
    }

    private fun playNotification() {
        Log.d("MainActivity", "playNotification")
        val soundUri = RingtoneManager.getActualDefaultRingtoneUri(
            applicationContext,
            RingtoneManager.TYPE_NOTIFICATION,
        )
        Log.d("MainActivity", "soundUri=$soundUri")
        val r = RingtoneManager.getRingtone(applicationContext, soundUri)
        r.play()
    }

    private fun testFontFamily() {
        val fontFamilyResId = this.queryFontFamilyAttrResId()
        val fontFamilyName = this.queryFontFamilyName()
        Log.d("MainActivity", "fontFamilyResId: $fontFamilyResId, fontFamilyName: $fontFamilyName")
    }

    private fun testStorage() {
        Log.d(
            "MainActivity",
            "Environment.getExternalStorageDirectory()=${Environment.getExternalStorageDirectory()}"
        )

        val file = File("/")
        file.setWritable(true)
        Log.d("MainActivity", "file=$file exists=${file.exists()}")
        // file=/

        val file1 = Environment.getExternalStorageDirectory()
        Log.d("MainActivity", "file1=$file1 exists=${file1.exists()}")
        // file=/storage/emulated/10

        val file12 = File(file1, "hoge")
        Log.d("MainActivity", "file12=$file12 exists=${file12.exists()}")
        val result = file12.mkdir()
        Log.d("MainActivity", "mkdir result=$result")

        val file2 = File("/product/")
        Log.d("MainActivity", "file2=$file2 exists=${file2.exists()}")
        // file=/product

        val file3 = File("/", "hoge")
        Runtime.getRuntime().exec("chmod 777 " + file3.path)
        Log.d("MainActivity", "file3=$file3 exists=${file3.exists()}")
        file3.setWritable(true)
        file3.mkdir()
        Log.d("MainActivity", "file3=$file3 exists=${file3.exists()}")

        val downloadFile =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        Log.d("MainActivity", "downloadFile=$downloadFile exists=${downloadFile.exists()}")
        val file4 = File(downloadFile, "test2")
        Log.d("MainActivity", "file4=$file4 exists=${file4.exists()}")
        file4.createNewFile()
        Log.d("MainActivity", "file4=$file4 exists=${file4.exists()}")
    }
}