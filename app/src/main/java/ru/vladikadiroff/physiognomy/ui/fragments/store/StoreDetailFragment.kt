package ru.vladikadiroff.physiognomy.ui.fragments.store

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_physignomy.*
import kotlinx.android.synthetic.main.fragment_store_detail.*
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.extensions.startAnimationFromResources
import ru.vladikadiroff.physiognomy.extensions.startFallDownAnimation
import ru.vladikadiroff.physiognomy.ui.activities.PhysiognomyActivity
import ru.vladikadiroff.physiognomy.viewmodels.store.StoreDetailViewModel

class StoreDetailFragment: Fragment(R.layout.fragment_store_detail) {

    val viewModel by lazy { ViewModelProvider(this).get(StoreDetailViewModel::class.java)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        val data = viewModel.getArgs()

        toolbarStoreDetail.setNavigationIcon(R.drawable.ic_button_error_white)
        toolbarStoreDetail.setNavigationOnClickListener { findNavController().navigateUp() }


        collapsingToolbarLayoutStoreDetail.title = data.name
        textStoreDetailTitle.text = data.description
        textStoreDetailInfo.text = data.text
        textStoreDetailPrice.text = "${data.price}\u20BD"
        Glide
            .with(this)
            .load(data.picture)
            .into(imageStoreDetail)

        containerDetail.startFallDownAnimation()
    }

    private fun showMainToolbar(show: Boolean){
        val toolbar = (activity as PhysiognomyActivity).toolbar
        if (show) toolbar?.visibility = View.VISIBLE
        else toolbar?.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        showMainToolbar(false)
    }

    override fun onStop() {
        super.onStop()
        showMainToolbar(true)
    }

}