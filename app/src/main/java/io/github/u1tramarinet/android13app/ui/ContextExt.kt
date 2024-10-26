package io.github.u1tramarinet.android13app.ui

import android.content.Context
import android.util.Log
import android.util.TypedValue
import androidx.annotation.StyleRes

fun Context.queryFontFamilyAttrResId(): Int? = obtainStyledAttributes(
    intArrayOf(android.R.attr.fontFamily)
).let {
    val fontFamilyResId = it.getResourceId(0, -1)
    it.recycle()
    if (fontFamilyResId != -1) {
        Log.d("queryFontFamilyAttrResId", "fontFamilyResId: $fontFamilyResId")
        fontFamilyResId
    } else {
        Log.d("queryFontFamilyAttrResId", "fontFamilyResId: unknown")
        null
    }
}

@StyleRes
fun Context.queryThemeAttrResId(): Int? = obtainStyledAttributes(
    intArrayOf(android.R.attr.theme)
).let {
    val themeResId = it.getResourceId(0, -1)
    it.recycle()
    if (themeResId != -1) {
        Log.d("queryThemeAttrResId", "themeResId: $themeResId")
        themeResId
    } else {
        Log.d("queryThemeAttrResId", "themeResId: unknown")
        null
    }
}

fun Context.queryFontFamilyName(): String? {
    val typedValue = TypedValue()
    theme.resolveAttribute(
        android.R.attr.fontFamily, typedValue, true
    )
    return typedValue.string as String?
}