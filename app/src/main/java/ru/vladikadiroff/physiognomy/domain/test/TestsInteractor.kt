package ru.vladikadiroff.physiognomy.domain.test

import androidx.lifecycle.MutableLiveData
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsLoadingStatus
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsResultLoadingStatus
import ru.vladikadiroff.physiognomy.domain.test.models.TestModel

interface TestsInteractor {
    fun loadTests()
    fun loadResult()
    fun getTest(): TestModel
    fun setCurrentTest(testId: Int)
    fun setAnswer(answer: Int, count: Int)
    fun getTestsLoadingStatus(): MutableLiveData<TestsLoadingStatus>
    fun getTestResultLoadingStatus(): MutableLiveData<TestsResultLoadingStatus>
}
