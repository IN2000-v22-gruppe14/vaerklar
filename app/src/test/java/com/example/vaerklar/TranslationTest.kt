package com.example.vaerklar

import com.example.vaerklar.data.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TranslationTest {
    @Test
    fun canTranslateWeekDay() {
        val translation = translateWeekdayAbbreviation("MO")
        assertEquals("MA", translation)
    }

    @Test
    fun canTranslateNull() {
        val translation = translateWeekdayAbbreviation("yeet")
        assertEquals("...", translation)
    }
}
