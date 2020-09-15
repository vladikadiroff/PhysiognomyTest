package ru.vladikadiroff.physiognomy.viewmodels.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vladikadiroff.physiognomy.PhysiognomyApplication
import ru.vladikadiroff.physiognomy.domain.test.TestsInteractorImpl
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsLoadingStatus
import javax.inject.Inject

class TestsViewModel: ViewModel() {

    @Inject
    lateinit var interactor: TestsInteractorImpl

    val state: MutableLiveData<TestsLoadingStatus>

    init {
        PhysiognomyApplication.instance.testsComponent.inject(viewModel = this)
        state = interactor.getTestsLoadingStatus()
        interactor.loadTests()
    }

    fun onChoiceTest(testId: Int){
        interactor.setCurrentTest(testId = testId)
    }

    fun onRetry(){
        interactor.loadTests()
    }

}