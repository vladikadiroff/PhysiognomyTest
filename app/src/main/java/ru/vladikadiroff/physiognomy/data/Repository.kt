package ru.vladikadiroff.physiognomy.data

import io.reactivex.Single
import ru.vladikadiroff.physiognomy.data.models.store.StoreAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestQuestionsAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestResultAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestAPI

interface Repository {
    fun getStoreItems(): Single<List<StoreAPI>>
    fun getTestQuestions(testId: String): Single<List<TestQuestionsAPI>>
    fun getTestResult(answers: String): Single<List<TestResultAPI>>
    fun getTests(): Single<List<TestAPI>>
}