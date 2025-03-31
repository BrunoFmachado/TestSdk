package com.example.ui.extensions

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.example.smartpossdk.R

fun View.applyFadeInAnim(context: Context) {
    val anim = AnimationUtils.loadAnimation(context, R.anim.fade_scale_in)
    startAnimation(anim)
}
