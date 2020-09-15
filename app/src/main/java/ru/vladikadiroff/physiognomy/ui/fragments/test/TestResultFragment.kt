package ru.vladikadiroff.physiognomy.ui.fragments.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_test_result.*
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsResultLoadingStatus
import ru.vladikadiroff.physiognomy.extensions.convertToString
import ru.vladikadiroff.physiognomy.extensions.startFallDownAnimation
import ru.vladikadiroff.physiognomy.ui.activities.PhysiognomyActivity
import ru.vladikadiroff.physiognomy.ui.adapters.test.TestResultAdapter
import ru.vladikadiroff.physiognomy.viewmodels.test.TestResultViewModel

class TestResultFragment: Fragment(R.layout.fragment_test_result){

    private val adapter = TestResultAdapter()
    private val TAG = TestResultFragment::class.java.name
    private val viewModel by lazy { ViewModelProvider(this).get(TestResultViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initViewModel()
    }

    private fun initView(view: View){

        buttonStartTestAgain.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_testResultFragment_to_testStartScreenFragment) }

        buttonResultRetry.setOnClickListener { viewModel.onRetryGetResult() }

        with(recyclerResult){
            adapter = this@TestResultFragment.adapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is TestsResultLoadingStatus.Done -> {
                    adapter.setAdapter(state.result)
                    showResult()
                }
                is TestsResultLoadingStatus.IsLoading -> showLoading()
                is TestsResultLoadingStatus.Exception<*> -> {
                    showException()
                    Log.e(TAG, convertToString(state.exception))
                }
            }})
    }

    private fun showResult(){
        showLoading(false)
        layoutResultException.visibility = View.GONE
        layoutResult.visibility = View.VISIBLE
        layoutResult.startFallDownAnimation()
    }

    private fun showException(){
        showLoading(false)
        layoutResult.visibility = View.GONE
        layoutResultException.visibility = View.VISIBLE
        layoutResultException.startFallDownAnimation()
    }

    private fun showLoading(show: Boolean = true){
        if(show){
            layoutResult.visibility = View.GONE
            layoutResultException.visibility = View.GONE
            layoutLoadingResult.visibility = View.VISIBLE
            layoutLoadingResult.startShimmerAnimation()
            progressLoadingResult.visibility = View.VISIBLE
            progressLoadingResult.isIndeterminate = true
        } else {
            layoutLoadingResult.visibility = View.GONE
            layoutLoadingResult.stopShimmerAnimation()
            progressLoadingResult.visibility = View.GONE
            progressLoadingResult.isIndeterminate = false
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as PhysiognomyActivity).supportActionBar?.title = viewModel.getTestName()
    }
}