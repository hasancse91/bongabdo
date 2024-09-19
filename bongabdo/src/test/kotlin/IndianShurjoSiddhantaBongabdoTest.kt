import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoMethod
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.Calendar

class IndianShurjoSiddhantaBongabdoTest {

    @ParameterizedTest
    @CsvSource(
        "2024, 3, 14, '১ বৈশাখ, ১৪৩১'",
        "2024, 4, 14, '৩১ বৈশাখ, ১৪৩১'",
        "2024, 4, 15, '১ জ্যৈষ্ঠ, ১৪৩১'",
        "2024, 5, 15, '৩২ জ্যৈষ্ঠ, ১৪৩১'",
        "2024, 5, 16, '১ আষাঢ়, ১৪৩১'",
        "2024, 6, 16, '৩১ আষাঢ়, ১৪৩১'",
        "2024, 6, 17, '১ শ্রাবণ, ১৪৩১'",
        "2024, 7, 17, '৩২ শ্রাবণ, ১৪৩১'",
        "2024, 7, 18, '১ ভাদ্র, ১৪৩১'",
        "2024, 8, 17, '৩১ ভাদ্র, ১৪৩১'",
        "2024, 8, 18, '১ আশ্বিন, ১৪৩১'",
        "2024, 9, 17, '৩০ আশ্বিন, ১৪৩১'",
        "2024, 9, 18, '১ কার্তিক, ১৪৩১'",
        "2024, 10, 16, '৩০ কার্তিক, ১৪৩১'",
        "2024, 10, 17, '১ অগ্রহায়ণ, ১৪৩১'",
        "2024, 11, 16, '৩০ অগ্রহায়ণ, ১৪৩১'",
        "2024, 11, 17, '১ পৌষ, ১৪৩১'",
        "2025, 0, 14, '২৯ পৌষ, ১৪৩১'",
        "2025, 0, 15, '১ মাঘ, ১৪৩১'",
        "2025, 1, 13, '৩০ মাঘ, ১৪৩১'",
        "2025, 1, 14, '১ ফাল্গুন, ১৪৩১'",
        "2025, 2, 14, '২৯ ফাল্গুন, ১৪৩১'",
        "2025, 2, 15, '১ চৈত্র, ১৪৩১'",
        "2025, 3, 14, '৩১ চৈত্র, ১৪৩১'",
    )
    fun testBongabdo_Year_1431(year: Int, month: Int, day: Int, expectedDate: String) {
        assertBongabdoDate(year, month, day, expectedDate)
    }

    @ParameterizedTest
    @CsvSource(
        "2026, 3, 15, '১ বৈশাখ, ১৪৩৩'",
        "2026, 4, 15, '৩১ বৈশাখ, ১৪৩৩'",
        "2026, 4, 16, '১ জ্যৈষ্ঠ, ১৪৩৩'",
        "2026, 5, 15, '৩১ জ্যৈষ্ঠ, ১৪৩৩'",
        "2026, 5, 16, '১ আষাঢ়, ১৪৩৩'",
        "2026, 6, 17, '৩২ আষাঢ়, ১৪৩৩'",
        "2026, 6, 18, '১ শ্রাবণ, ১৪৩৩'",
        "2026, 7, 18, '৩২ শ্রাবণ, ১৪৩৩'",
        "2026, 7, 19, '১ ভাদ্র, ১৪৩৩'",
        "2026, 8, 18, '৩১ ভাদ্র, ১৪৩৩'",
        "2026, 8, 19, '১ আশ্বিন, ১৪৩৩'",
        "2026, 9, 18, '৩০ আশ্বিন, ১৪৩৩'",
        "2026, 9, 19, '১ কার্তিক, ১৪৩৩'",
        "2026, 10, 17, '৩০ কার্তিক, ১৪৩৩'",
        "2026, 10, 18, '১ অগ্রহায়ণ, ১৪৩৩'",
        "2026, 11, 16, '২৯ অগ্রহায়ণ, ১৪৩৩'",
        "2026, 11, 17, '১ পৌষ, ১৪৩৩'",
        "2027, 0, 15, '৩০ পৌষ, ১৪৩৩'",
        "2027, 0, 16, '১ মাঘ, ১৪৩৩'",
        "2027, 1, 13, '২৯ মাঘ, ১৪৩৩'",
        "2027, 1, 14, '১ ফাল্গুন, ১৪৩৩'",
        "2027, 2, 15, '৩০ ফাল্গুন, ১৪৩৩'",
        "2027, 2, 16, '১ চৈত্র, ১৪৩৩'",
        "2027, 3, 14, '৩০ চৈত্র, ১৪৩৩'",
    )
    fun testBongabdo_Year_1433(year: Int, month: Int, day: Int, expectedDate: String) {
        assertBongabdoDate(year, month, day, expectedDate)
    }

    private fun assertBongabdoDate(year: Int, month: Int, day: Int, expectedDate: String) {
        val bongabdo: Bongabdo = Bongabdo.getInstance(BongabdoMethod.INDIAN_SHURJO_SIDDHANTA)
        val calendar: Calendar = Calendar.getInstance()

        calendar.set(year, month, day)
        Assertions.assertEquals(expectedDate, bongabdo.getBongabdoData(calendar).getFullDate())
    }
}