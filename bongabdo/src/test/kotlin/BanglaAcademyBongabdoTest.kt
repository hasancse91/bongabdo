import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoMethod
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.Calendar

class BanglaAcademyBongabdoTest {

    private fun assertBongabdoDate(year: Int, month: Int, day: Int, expectedDate: String) {
        val bongabdo: Bongabdo = Bongabdo.getInstance(BongabdoMethod.BANGLA_ACADEMY)
        val calendar: Calendar = Calendar.getInstance()

        calendar.set(year, month, day)
        assertEquals(expectedDate, bongabdo.getBongabdoData(calendar).getFullDate())
    }

    @ParameterizedTest
    @CsvSource(
        "2020, 0, 1, '১৭ পৌষ, ১৪২৬'",
        "2020, 0, 31, '১৭ মাঘ, ১৪২৬'",
        "2020, 1, 18, '৫ ফাল্গুন, ১৪২৬'",
        "2020, 1, 28, '১৫ ফাল্গুন, ১৪২৬'",
        "2020, 1, 29, '১৬ ফাল্গুন, ১৪২৬'",
        "2020, 2, 1, '১৭ ফাল্গুন, ১৪২৬'",
        "2020, 2, 5, '২১ ফাল্গুন, ১৪২৬'",
        "2020, 2, 31, '১৭ চৈত্র, ১৪২৬'",
        "2020, 3, 1, '১৮ চৈত্র, ১৪২৬'",
        "2020, 3, 13, '৩০ চৈত্র, ১৪২৬'",
        "2020, 3, 14, '১ বৈশাখ, ১৪২৭'",
        "2020, 3, 30, '১৭ বৈশাখ, ১৪২৭'",
        "2020, 4, 1, '১৮ বৈশাখ, ১৪২৭'",
        "2028, 3, 1, '১৮ চৈত্র, ১৪৩৪'",
        "2028, 3, 13, '৩০ চৈত্র, ১৪৩৪'",
        "2028, 3, 14, '১ বৈশাখ, ১৪৩৫'",
        "2028, 5, 1, '১৮ জ্যৈষ্ঠ, ১৪৩৫'",
        "2028, 5, 30, '১৬ আষাঢ়, ১৪৩৫'",
        "2028, 8, 1, '১৭ ভাদ্র, ১৪৩৫'",
        "2028, 8, 30, '১৫ আশ্বিন, ১৪৩৫'",
        "2028, 11, 1, '১৬ অগ্রহায়ণ, ১৪৩৫'",
        "2028, 11, 31, '১৬ পৌষ, ১৪৩৫'"
    )
    fun testBongabdo_leapYear(year: Int, month: Int, day: Int, expectedDate: String) {
        assertBongabdoDate(year, month, day, expectedDate)
    }

    @ParameterizedTest
    @CsvSource(
        "2021, 0, 1, '১৭ পৌষ, ১৪২৭'",
        "2021, 0, 31, '১৭ মাঘ, ১৪২৭'",
        "2021, 1, 18, '৫ ফাল্গুন, ১৪২৭'",
        "2021, 1, 28, '১৫ ফাল্গুন, ১৪২৭'",
        "2021, 2, 1, '১৬ ফাল্গুন, ১৪২৭'",
        "2021, 2, 31, '১৭ চৈত্র, ১৪২৭'",
        "2021, 3, 1, '১৮ চৈত্র, ১৪২৭'",
        "2021, 3, 13, '৩০ চৈত্র, ১৪২৭'",
        "2021, 3, 14, '১ বৈশাখ, ১৪২৮'",
        "2021, 3, 30, '১৭ বৈশাখ, ১৪২৮'",
        "2021, 4, 1, '১৮ বৈশাখ, ১৪২৮'",
        "2021, 5, 1, '১৮ জ্যৈষ্ঠ, ১৪২৮'",
        "2021, 5, 30, '১৬ আষাঢ়, ১৪২৮'",
        "2021, 8, 1, '১৭ ভাদ্র, ১৪২৮'",
        "2021, 8, 30, '১৫ আশ্বিন, ১৪২৮'",
        "2021, 11, 1, '১৬ অগ্রহায়ণ, ১৪২৮'",
        "2021, 11, 31, '১৬ পৌষ, ১৪২৮'"
    )
    fun testBongabdo_nonLeapYear(year: Int, month: Int, day: Int, expectedDate: String) {
        assertBongabdoDate(year, month, day, expectedDate)
    }

    @ParameterizedTest
    @CsvSource(
        "2020, 1, 21, '৮ ফাল্গুন, ১৪২৬'",
        "2020, 2, 26, '১২ চৈত্র, ১৪২৬'",
        "2020, 4, 8, '২৫ বৈশাখ, ১৪২৭'",
        "2020, 4, 25, '১১ জ্যৈষ্ঠ, ১৪২৭'",
        "2020, 11, 16, '১ পৌষ, ১৪২৭'"
    )
    fun testBongabdo_specialDays_leapYear(year: Int, month: Int, day: Int, expectedDate: String) {
        assertBongabdoDate(year, month, day, expectedDate)
    }

    @ParameterizedTest
    @CsvSource(
        "2021, 1, 21, '৮ ফাল্গুন, ১৪২৭'",
        "2021, 2, 26, '১২ চৈত্র, ১৪২৭'",
        "2021, 4, 8, '২৫ বৈশাখ, ১৪২৮'",
        "2021, 4, 25, '১১ জ্যৈষ্ঠ, ১৪২৮'",
        "2021, 11, 16, '১ পৌষ, ১৪২৮'"
    )
    fun testBongabdo_specialDays_nonLeapYear(year: Int, month: Int, day: Int, expectedDate: String) {
        assertBongabdoDate(year, month, day, expectedDate)
    }
}