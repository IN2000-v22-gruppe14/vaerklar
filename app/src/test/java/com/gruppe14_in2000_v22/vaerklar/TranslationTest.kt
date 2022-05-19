package com.gruppe14_in2000_v22.vaerklar

import com.gruppe14_in2000_v22.vaerklar.data.translateWeekDayWhole
import com.gruppe14_in2000_v22.vaerklar.data.translateWeekdayAbbreviation
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TranslationTest {
    @Test
    fun abbreviationToAbbreviation() {
        val translation = translateWeekdayAbbreviation("MO")
        assertEquals("MA", translation)
    }

    @Test
    fun abbreviationToAbbreviationGarbled() {
        val translation = translateWeekdayAbbreviation("yeet")
        assertEquals("...", translation)
    }

    @Test
    fun abbreviationToFull() {
        val translation = translateWeekDayWhole("FR")
        assertEquals("FREDAG", translation)
    }

    @Test
    fun abbreviationToFullGarbled() {
        val translation = translateWeekDayWhole("yeet")
        assertEquals("...", translation)
    }
}
