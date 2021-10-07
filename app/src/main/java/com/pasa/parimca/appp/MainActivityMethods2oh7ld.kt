package com.pasa.parimca.appp

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

fun MainActivity2oh7ld.clickSetup2oh7ld (uiComponents: (view2oh7ld: View) -> Unit) {
    findViewById<View>(R.id.v_2oh7ld).setOnClickListener {
        uiComponents(it)
    }
    findViewById<TextView>(R.id.tv_start_2oh7ld).setOnClickListener {
        uiComponents(it)
    }
}

fun MainActivity2oh7ld.animationBackGround2oh7ld () {
    ValueAnimator.ofArgb(resources.getColor(R.color.black_2oh7ld), resources.getColor(R.color.theme_yellow_2_2oh7ld)).run {
        setEvaluator(ArgbEvaluator())
        duration = 5000
        addUpdateListener { findViewById<ConstraintLayout>(R.id.cl_second_2oh7ld).setBackgroundColor(it.animatedValue.toString().toInt()) }
        start()
    }
}