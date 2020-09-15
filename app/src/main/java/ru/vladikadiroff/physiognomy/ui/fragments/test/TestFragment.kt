package ru.vladikadiroff.physiognomy.ui.fragments.test

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_test.*
import kotlinx.android.synthetic.main.fragment_test.view.*
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.extensions.setTextWithFadeAnimation
import ru.vladikadiroff.physiognomy.ui.abstracts.FragmentWithLayoutManager
import ru.vladikadiroff.physiognomy.ui.activities.PhysiognomyActivity
import ru.vladikadiroff.physiognomy.ui.adapters.test.TestAdapter
import ru.vladikadiroff.physiognomy.viewmodels.test.TestViewModel

class TestFragment: FragmentWithLayoutManager(R.layout.fragment_test){

    private val adapter = TestAdapter()
    private val viewModel by lazy { ViewModelProvider(this).get(TestViewModel::class.java) }

    override var layoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel(view)
        initView(view)
    }

    private fun initView(view: View) {

        adapter.onItemClick = { viewModel.onAnswer(answer = it) }

        with(view.recyclerTestItems) {
            adapter = this@TestFragment.adapter
            layoutManager = this@TestFragment.layoutManager
        }

    }

    private fun initViewModel(view: View) {
        viewModel.title.observe(viewLifecycleOwner, Observer { view.textTestTitle.setTextWithFadeAnimation(it) })
        viewModel.description.observe(viewLifecycleOwner, Observer { view.textTestDescription.setTextWithFadeAnimation(it) })
        viewModel.listVariants.observe(viewLifecycleOwner, Observer {
            adapter.setFacialFeatures(it)
            view.recyclerTestItems.scheduleLayoutAnimation()
        })
        viewModel.questionsCount.observe(viewLifecycleOwner, Observer { view.textTestCounter.text = getString(R.string.question_count) + " $it" })
        viewModel.finishTest.observe(viewLifecycleOwner, Observer { finish ->
            if(finish) {
                    if (findNavController().currentDestination?.id == R.id.testRunFragment) {
                        findNavController().navigate(R.id.action_testRunFragment_to_testResultFragment)
                    }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as PhysiognomyActivity).supportActionBar?.title = viewModel.getTestName()
    }

}