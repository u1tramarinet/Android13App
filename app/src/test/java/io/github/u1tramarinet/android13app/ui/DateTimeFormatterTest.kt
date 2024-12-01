package io.github.u1tramarinet.android13app.ui

import org.junit.Test
import java.time.LocalDate
import java.time.chrono.IsoChronology
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.FormatStyle
import java.util.Locale

class DateTimeFormatterTest {
    @Test
    fun test_date1() {
        val dateTime = LocalDate.of(2024, 11, 22)
        println()
        printFormatter(dateTime, Locale.JAPAN)
        printFormatter(dateTime, Locale.US)
        printFormatter(dateTime, Locale.UK)
        printFormatter(dateTime, Locale.CHINA)
        printFormatter(dateTime, Locale.GERMAN)
    }

    @Test
    fun test_date2() {
        val dateTime = LocalDate.of(2024, 1, 2)
        println()
        printFormatter(dateTime, Locale.JAPAN)
        printFormatter(dateTime, Locale.US)
        printFormatter(dateTime, Locale.UK)
        printFormatter(dateTime, Locale.CHINA)
        printFormatter(dateTime, Locale.GERMAN)
    }

    @Test
    fun test_date3() {
        println()
        printFormatter2(Locale.JAPAN)
        printFormatter2(Locale.US)
        printFormatter2(Locale.UK)
        printFormatter2(Locale.CHINA)
        printFormatter2(Locale.GERMAN)
        printFormatter2(Locale.US)
    }

    private fun printFormatter(date: LocalDate, locale: Locale) {
        val shortFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale)
        val mediumFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale)
        val longFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale)
        val fullFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale)
        val bestFormatter = getBestDateFormatter(locale)
        println("locale=${locale.language}")
        println("SHORT: ${date.format(shortFormatter)}")
        println("LONG: ${date.format(longFormatter)}")
        println("MEDIUM: ${date.format(mediumFormatter)}")
        println("FULL: ${date.format(fullFormatter)}")
        println("BEST: ${date.format(bestFormatter)}")
        println()
    }

    private fun printFormatter2(locale: Locale) {
        val yearVal = 2033
        val monthVal = 11
        val dayVal = 22
        val dateTime = LocalDate.of(yearVal, monthVal, dayVal)

        println("locale=${locale.language}")

        val year1 = DateTimeFormatter.ofPattern("yy", locale).format(dateTime)
        val year2 = DateTimeFormatter.ofPattern("yyyy", locale).format(dateTime)
        println("year=$yearVal->$year1/$year2")

        val month1 = DateTimeFormatter.ofPattern("M", locale).format(dateTime)
        val month2 = DateTimeFormatter.ofPattern("MM", locale).format(dateTime)
        val month3 = DateTimeFormatter.ofPattern("MMMM", locale).format(dateTime)
        println("month=$monthVal->$month1/$month2/$month3")

        val day = DateTimeFormatter.ofPattern("dd", locale).format(dateTime)
        println("day=$dayVal->$day")

        val date = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale).format(dateTime)
        println("date=$yearVal/$monthVal/$dayVal->$date")

        val best = getBestDateFormatter(locale).format(dateTime)
        println("best=$yearVal/$monthVal/$dayVal->$best")

        val format = DateTimeFormatterBuilder.getLocalizedDateTimePattern(FormatStyle.SHORT, FormatStyle.SHORT, IsoChronology.INSTANCE, locale)
        println("format=$format")

        println()
    }
}