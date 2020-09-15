package ru.vladikadiroff.physiognomy.extensions

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.annotation.AnimRes
import ru.vladikadiroff.physiognomy.R

fun TextView.setTextWithFadeAnimation(text: String){

    this.startAnimationFromResources(R.anim.transition_animation)
    this.text = text
}

fun View.startFallDownAnimation(){
    this.startAnimationFromResources(R.anim.fall_down_animation)
}

fun View.startAnimationFromResources(@AnimRes animation: Int){
    val anim = AnimationUtils.loadAnimation(this.context, animation)
    this.startAnimation(anim)
}
