package ru.vladikadiroff.physiognomy.domain.test.keys

import ru.vladikadiroff.physiognomy.domain.test.models.TestResultModel

sealed class TestsResultLoadingStatus {
    object Default: TestsResultLoadingStatus()
    object IsLoading: TestsResultLoadingStatus()
    class Done(val result: List<TestResultModel>): TestsResultLoadingStatus()
    class Exception<T>(val exception: T): TestsResultLoadingStatus()
}