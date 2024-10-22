package com.hellohasan.bongabdo.api

import com.hellohasan.bongabdo.localization.BengaliLocalizationConfig
import com.hellohasan.bongabdo.localization.BongabdoLocalizationConfig
import com.hellohasan.bongabdo.methods.banglaAcademy.BanglaAcademyBongabdo
import com.hellohasan.bongabdo.methods.indianDrikSiddhanta.IndianDrikSiddhantaBongabdo

abstract class Bongabdo {

    var localizationConfig: BongabdoLocalizationConfig = BengaliLocalizationConfig()

    companion object {

        /**
         * We have two available calculation methods: [BongabdoMethod.BANGLA_ACADEMY] and [BongabdoMethod.INDIAN_DRIK_SIDDHANTA].
         * We already implemented these methods and logics. So you can easily use these methods by
         * calling [getInstance] method.
         * But if you need any other calculation method, just extend [Bongabdo] class and implement
         * your own logic inside [getBongabdoData] method.
         */
        @JvmStatic
        fun getInstance(method: BongabdoMethod): Bongabdo {
            return when (method) {
                BongabdoMethod.BANGLA_ACADEMY -> BanglaAcademyBongabdo()
                BongabdoMethod.INDIAN_DRIK_SIDDHANTA -> IndianDrikSiddhantaBongabdo()
            }
        }
    }

    /**
     * Input Gregorian calendar Data.
     * [month] is 0-based. January=0, December=11
     */
    abstract fun getBongabdoData(year: Int, month: Int, day: Int): BongabdoData
}