package ru.vladikadiroff.physiognomy.ui.fragments.store

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_store.*
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.domain.store.keys.StoreLoadingStatus
import ru.vladikadiroff.physiognomy.ui.abstracts.FragmentWithLayoutManager
import ru.vladikadiroff.physiognomy.ui.adapters.store.StoreAdapter
import ru.vladikadiroff.physiognomy.extensions.convertToString
import ru.vladikadiroff.physiognomy.extensions.startFallDownAnimation
import ru.vladikadiroff.physiognomy.viewmodels.store.StoreViewModel

class StoreFragment: FragmentWithLayoutManager(R.layout.fragment_store){

    override var gridLayoutCount: Int = 2
    override var layoutManagerType: LayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER

    private val adapter = StoreAdapter()
    private val TAG = StoreFragment::class.java.name
    private val viewModel by lazy { ViewModelProvider(this).get(StoreViewModel::class.java)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }


    private fun initView(){

        buttonStoreRetry.setOnClickListener { viewModel.onRetryGetStore() }

        adapter.onItemClick = {
            viewModel.safeArgs(it)
            if (findNavController().currentDestination?.id == R.id.shopFragment) {
                findNavController().navigate(R.id.action_shopFragment_to_shopItemFragment)
            }
        }

        with(recyclerStore){
            adapter = this@StoreFragment.adapter
            layoutManager = this@StoreFragment.layoutManager
        }
    }


    private fun initViewModel(){
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is StoreLoadingStatus.IsLoading -> showLoading()
                is StoreLoadingStatus.Done -> {
                    adapter.updateStore(state.list)
                    showStore()
                }
                is StoreLoadingStatus.Exception<*> -> {
                    showException()
                    Log.e(TAG, convertToString(state.message))
                }
            }})
    }

    private fun showStore(){
        showLoading(false)
        layoutStoreException.visibility = View.GONE
        recyclerStore.visibility = View.VISIBLE
        recyclerStore.scheduleLayoutAnimation()
    }

    private fun showException(){
        showLoading(false)
        recyclerStore.visibility = View.GONE
        layoutStoreException.visibility = View.VISIBLE
        layoutStoreException.startFallDownAnimation()
    }

    private fun showLoading(show: Boolean = true){
        if(show) {
            recyclerStore.visibility = View.GONE
            layoutStoreException.visibility = View.GONE
            shimmerStore.visibility = View.VISIBLE
            progressLoadingStore.visibility = View.VISIBLE
            shimmerStore.startShimmerAnimation()
            progressLoadingStore.isIndeterminate = true
        } else {
            shimmerStore.visibility = View.GONE
            progressLoadingStore.visibility = View.GONE
            shimmerStore.stopShimmerAnimation()
            progressLoadingStore.isIndeterminate = true
        }
    }

}