package com.pasa.parimca.appp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.pasa.parimca.appp.Utilities2oh7ld.internetChecker2oh7ld
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InternetActivity2oh7ld : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_activity2oh7ld)
        lifecycleScope.launch {
            delay(500)
            ObjectAnimator.ofFloat(findViewById(R.id.tv_internet_2oh7ld), View.ALPHA, 0f, 1f)
                .run {
                    duration = 400
                    start()
                }
            ObjectAnimator.ofFloat(findViewById(R.id.b_internet_2oh7ld), View.ALPHA, 0f, 1f)
                .run {
                    duration = 400
                    start()
                }
        }
        findViewById<Button>(R.id.b_internet_2oh7ld).setOnClickListener {
            if (internetChecker2oh7ld()) {
                ObjectAnimator.ofFloat(findViewById(R.id.tv_internet_2oh7ld), View.ALPHA, 1f, 0f)
                    .run {
                        duration = 400
                        start()
                    }
                ObjectAnimator.ofFloat(findViewById(R.id.b_internet_2oh7ld), View.ALPHA, 1f, 0f)
                    .run {
                        duration = 400
                        start()
                        doOnEnd {
                            startActivity(
                                Intent(
                                    this@InternetActivity2oh7ld,
                                    WebActivity2oh7ld::class.java
                                ),
                                ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    this@InternetActivity2oh7ld,
                                    findViewById(R.id.v_web_2oh7ld),
                                    "view_in"
                                ).toBundle()
                            )
                        }
                    }
            }
        }


    }

    override fun onStop() {
        finish()
        super.onStop()
    }
}