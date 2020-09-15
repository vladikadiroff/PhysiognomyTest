package ru.vladikadiroff.physiognomy.ui.fragments.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import kotlinx.android.synthetic.main.fragment_tests.*
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsLoadingStatus
import ru.vladikadiroff.physiognomy.extensions.convertToString
import ru.vladikadiroff.physiognomy.extensions.startFallDownAnimation
import ru.vladikadiroff.physiognomy.ui.adapters.test.TestsAdapter
import ru.vladikadiroff.physiognomy.viewmodels.test.TestsViewModel
import kotlin.math.abs


class TestsFragment: Fragment(R.layout.fragment_tests){

    private var adapter = TestsAdapter()
    private val TAG = TestsFragment::class.java.name
    private val viewModel by lazy { ViewModelProvider(this).get(TestsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView(){
        adapter.onItemClick = {
            viewModel.onChoiceTest(it)
            if (findNavController().currentDestination?.id == R.id.testStartScreenFragment) {
                findNavController().navigate(R.id.action_testStartScreenFragment_to_testRunFragment)
            }
        }

        initViewPager()
        buttonStartRetry.setOnClickListener { viewModel.onRetry() }

    }

    private fun initViewPager(){
        viewPagerTests.adapter = adapter
        viewPagerTests.offscreenPageLimit = 3
        viewPagerTests.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositeTransformer = CompositePageTransformer()
        compositeTransformer.addTransformer(MarginPageTransformer(40))
        compositeTransformer.addTransformer { page, position ->
            page.scaleY = 0.85f + (1 - abs(position)) * 0.15f
        }

        viewPagerTests.setPageTransformer(compositeTransformer)
    }

    private fun initViewModel(){
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is TestsLoadingStatus.Done -> {
                    adapter.setTests(state.tests)
                    showTests()
                }
                is TestsLoadingStatus.IsLoading -> showLoading()
                is TestsLoadingStatus.Exception<*> -> {
                    showException()
                    Log.e(TAG, convertToString(state.exception))}
            }
        })
    }

    private fun showTests(){
        showLoading(false)
        layoutExceptionTests.visibility = View.GONE
        viewPagerTests.visibility = View.VISIBLE
        viewPagerTests.scheduleLayoutAnimation()
    }

    private fun showException(){
        showLoading(false)
        viewPagerTests.visibility = View.GONE
        layoutExceptionTests.visibility = View.VISIBLE
        layoutExceptionTests.startFallDownAnimation()
    }

    private fun showLoading(show: Boolean = true){
        if(show){
            viewPagerTests.visibility = View.GONE
            layoutExceptionTests.visibility = View.GONE
            layoutLoadingTests.visibility = View.VISIBLE
            layoutLoadingTests.startShimmerAnimation()
            progressLoadingTests.visibility = View.VISIBLE
            progressLoadingTests.isIndeterminate = true
        } else {
            layoutLoadingTests.visibility = View.GONE
            layoutLoadingTests.stopShimmerAnimation()
            progressLoadingTests.visibility = View.GONE
            progressLoadingTests.isIndeterminate = false
        }
    }

}