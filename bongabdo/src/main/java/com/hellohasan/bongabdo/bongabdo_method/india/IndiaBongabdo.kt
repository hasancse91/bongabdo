package com.hellohasan.bongabdo.bongabdo_method.india

import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoData
import com.hellohasan.bongabdo.bongabdo_method.BongabdoCoreConfig
import java.util.Calendar

class IndiaBongabdo : Bongabdo() {

    override fun getBongabdoCoreConfig(): BongabdoCoreConfig {
        return IndianBongabdoCoreConfig()
    }


    override fun getBongabdoData(calendar: Calendar): BongabdoData {
        TODO("Not yet implemented")
    }
}