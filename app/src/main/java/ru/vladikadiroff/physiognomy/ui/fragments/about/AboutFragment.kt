package ru.vladikadiroff.physiognomy.ui.fragments.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_about.view.*
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.extensions.startAnimationFromResources

class AboutFragment: Fragment(R.layout.fragment_about){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.containerAbout.startAnimationFromResources(R.anim.fall_down_animation)
    }
}

