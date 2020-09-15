package ru.vladikadiroff.physiognomy.data

import io.reactivex.Single
import ru.vladikadiroff.physiognomy.data.api.Service
import ru.vladikadiroff.physiognomy.data.models.store.StoreAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestAPI

class RepositoryImpl(private val service: Service): Repository {

    override fun getStoreItems(): Single<List<StoreAPI>> = service.getShopItems()

    override fun getTestQuestions(testId: String) = service.getTestQuestions()

    override fun getTestResult(answers: String) = service.getTestResult()

    override fun getTests(): Single<List<TestAPI>> = service.getTests()

}