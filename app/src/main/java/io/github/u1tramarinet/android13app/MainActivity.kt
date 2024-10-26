package io.github.u1tramarinet.android13app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.u1tramarinet.android13app.ui.queryFontFamilyAttrResId
import io.github.u1tramarinet.android13app.ui.queryFontFamilyName
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamilyResId = this.queryFontFamilyAttrResId()
        val fontFamilyName = this.queryFontFamilyName()
        Log.d("MainActivity", "fontFamilyResId: $fontFamilyResId, fontFamilyName: $fontFamilyName")
        setContent {
            Android13AppTheme {
                Android13App()
            }
        }
    }
}