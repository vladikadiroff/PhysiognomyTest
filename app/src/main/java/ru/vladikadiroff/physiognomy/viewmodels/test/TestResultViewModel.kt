package ru.vladikadiroff.physiognomy.viewmodels.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vladikadiroff.physiognomy.PhysiognomyApplication
import ru.vladikadiroff.physiognomy.domain.test.TestsInteractorImpl
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsResultLoadingStatus
import javax.inject.Inject

class TestResultViewModel: ViewModel() {

    @Inject
    lateinit var interactor: TestsInteractorImpl

    val state: MutableLiveData<TestsResultLoadingStatus>

    init {
        PhysiognomyApplication.instance.testsComponent.inject(viewModel = this)
        state = interactor.getTestResultLoadingStatus()
        interactor.loadResult()
    }

    fun onRetryGetResult(){
        interactor.loadResult()
    }

    fun getTestName() = interactor.getTest().name

}