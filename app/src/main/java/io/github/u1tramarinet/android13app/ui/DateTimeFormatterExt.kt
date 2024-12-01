package io.github.u1tramarinet.android13app.ui

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun getBestDateTimeFormatter(locale: Locale): DateTimeFormatter {
    return DateTimeFormatter.ofPattern("${LocalizedDatePattern.get(locale)} hh:mm", locale)
}

fun getBestDateFormatter(locale: Locale): DateTimeFormatter {
    return DateTimeFormatter.ofPattern(LocalizedDatePattern.get(locale))
}

private object LocalizedDatePattern {
    private val yearPatterns = listOf("yy", "yyyy")
    private val monthPatterns = listOf("MM", "MMMM")
    private val dayPatterns = listOf("d", "dd")
    private val localizedPatterns: MutableMap<Locale, String> = mutableMapOf()

    fun get(locale: Locale): String {
        return localizedPatterns.getOrPut(locale) {
            getInternal(locale)
        }
    }

    private fun getInternal(locale: Locale): String {
        val sample = LocalDate.of(2033, 11, 22)

        val yearSamples = yearPatterns.map {
            DateTimeFormatter.ofPattern(it, locale).format(sample)
        }
        val monthSamples = monthPatterns.map {
            DateTimeFormatter.ofPattern(it, locale).format(sample)
        }
        val daySamples = dayPatterns.map {
            DateTimeFormatter.ofPattern(it, locale).format(sample)
        }

        val date =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale).format(sample)

        var pattern: String = "yyyy/MM/dd"
        for (i in 0 until date.length) {
            val regex = date.substring(i)
            if (yearSamples.any { regex.startsWith(it) }) {
                break
            } else if (monthSamples.any { regex.startsWith(it) }) {
                pattern = "MM/dd/yyyy"
                break
            } else if (daySamples.any { regex.startsWith(it) }) {
                pattern = "dd/MM/yyyy"
                break
            }
        }
        return pattern
    }
}