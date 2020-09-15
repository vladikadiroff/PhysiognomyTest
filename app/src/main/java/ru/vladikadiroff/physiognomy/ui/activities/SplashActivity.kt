
package ru.vladikadiroff.physiognomy.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import ru.vladikadiroff.physiognomy.R

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAnimation()
    }

    private fun initAnimation(){
        val transitionAnim = AnimationUtils.loadAnimation(this, R.anim.transition_animation)
        imageSplash.startAnimation(transitionAnim)

        Handler().postDelayed({
            startActivity(Intent(this, PhysiognomyActivity::class.java))
            finish()
        }, 3000L)
    }

}
