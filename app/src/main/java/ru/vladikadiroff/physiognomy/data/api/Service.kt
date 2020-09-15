package ru.vladikadiroff.physiognomy.data.api

import io.reactivex.Single
import retrofit2.http.GET
import ru.vladikadiroff.physiognomy.data.models.store.StoreAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestQuestionsAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestResultAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestAPI

interface Service {

    @GET("./result.json")
    fun getTestResult(): Single<List<TestResultAPI>>

    @GET("./test.json")
    fun getTestQuestions(): Single<List<TestQuestionsAPI>>

    @GET("./tests.json")
    fun getTests(): Single<List<TestAPI>>

    @GET("./store.json")
    fun getShopItems(): Single<List<StoreAPI>>

}