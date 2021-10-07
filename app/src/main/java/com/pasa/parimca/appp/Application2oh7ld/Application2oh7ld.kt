package com.pasa.parimca.appp.Application2oh7ld

import android.app.Application

class Application2oh7ld : Application() {

    override fun onCreate() {
        appsFlyerSetup2oh7ld()
        oneSignal2oh7ld()
        mobileAdsSetup2oh7ld()
        super.onCreate()
    }
}