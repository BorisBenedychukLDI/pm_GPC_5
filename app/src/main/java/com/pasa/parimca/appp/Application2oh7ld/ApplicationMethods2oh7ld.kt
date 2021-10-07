package com.pasa.parimca.appp.Application2oh7ld

import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.pasa.parimca.appp.Utilities2oh7ld.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.mobileAdsSetup2oh7ld () {
    MobileAds.initialize(this)
    CoroutineScope(Dispatchers.IO).launch {
        try {
            AID2oh7ld =
                AdvertisingIdClient.getAdvertisingIdInfo(this@mobileAdsSetup2oh7ld)?.id
        } catch (e2oh7ld: Exception) {

        }
    }
}

fun Context.oneSignal2oh7ld () {
    OneSignal.initWithContext(this)
    OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
    OneSignal.setAppId(CODED_ONESIGNAL_2oh7ld.decodeString2oh7ld())
}

fun Context.appsFlyerSetup2oh7ld () {
    val appsFlyerConversion2oh7ld = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(dataMap2oh7ld: MutableMap<String, Any>?) {
            dataMap2oh7ld?.run {

                status2oh7ld =
                    if (getValue(APPSFLYER_STATUS_TAG_2oh7ld).toString() == "Organic") "Organic" else "Non-organic"

                val paramsArray2oh7ld = mutableListOf<String>()
                getValue(APPSFLYER_CAMPAIGN_TAG_2oh7ld)
                    .toString()
                    .split("||").drop(1)
                    .map {
                        paramsArray2oh7ld.add(it.split(":")[1])
                    }

                key2oh7ld = if (paramsArray2oh7ld.size >= 1) paramsArray2oh7ld[0] else "empty"
                sub22oh7ld = if (paramsArray2oh7ld.size >= 2) paramsArray2oh7ld[1] else "empty"
                sub32oh7ld = if (paramsArray2oh7ld.size >= 3) paramsArray2oh7ld[2] else "empty"


                mediaSource2oh7ld = getValue(APPSFLYER_MEDIA_SOURCE_TAG_2oh7ld).toString()
                afChannel2oh7ld = getValue(APPSFLYER_AF_CHANNEL_TAG_2oh7ld).toString()
                adGroup2oh7ld = getValue(APPSFLYER_ADGROUP_TAG_2oh7ld).toString()
                adSet22oh7ld = getValue(APPSFLYER_ADSET_TAG_2oh7ld).toString()



            }
        }

        override fun onConversionDataFail(p0: String?) {
        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
        }

        override fun onAttributionFailure(p0: String?) {
        }
    }
    AppsFlyerLib.getInstance().run {
        uid2oh7ld = getAppsFlyerUID(this@appsFlyerSetup2oh7ld)
        init(
            CODE_APPSFLYER_2oh7ld.decodeString2oh7ld(),
            appsFlyerConversion2oh7ld,
            this@appsFlyerSetup2oh7ld
        )
        start(this@appsFlyerSetup2oh7ld)
    }
}