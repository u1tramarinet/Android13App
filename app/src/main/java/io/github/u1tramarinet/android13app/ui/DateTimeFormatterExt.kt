package io.github.u1tramarinet.android13app.ui

import java.time.chrono.IsoChronology
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.FormatStyle
import java.util.Locale

fun getBestDateTimeFormatter(locale: Locale): DateTimeFormatter {
    return DateTimeFormatter.ofPattern("${LocalizedDatePattern.get(locale)} HH:mm")
}

fun getBestDateFormatter(locale: Locale): DateTimeFormatter {
    return DateTimeFormatter.ofPattern(LocalizedDatePattern.get(locale))
}

private object LocalizedDatePattern {
    private const val PATTERN_YEAR = "yyyy"
    private const val PATTERN_MONTH = "MM"
    private const val PATTERN_DAY = "dd"
    private val localizedPatterns: MutableMap<Locale, String> = mutableMapOf()

    fun get(locale: Locale): String {
        return localizedPatterns.getOrPut(locale) {
            getInternal(locale)
        }
    }

    private fun getInternal(locale: Locale): String {
        val pattern = DateTimeFormatterBuilder.getLocalizedDateTimePattern(
            FormatStyle.SHORT,
            null,
            IsoChronology.INSTANCE,
            locale,
        )

        val order = mutableSetOf<String>()
        for (c in pattern) {
            if (c == 'y') {
                order.add(PATTERN_YEAR)
            } else if (c == 'M') {
                order.add(PATTERN_MONTH)
            } else if (c == 'd') {
                order.add(PATTERN_DAY)
            }
            if (order.size == 3) {
                break
            }
        }
        return order.joinToString(separator = "/")
    }
}