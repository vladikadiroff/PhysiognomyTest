package ru.vladikadiroff.physiognomy.domain.test.keys

import ru.vladikadiroff.physiognomy.domain.test.models.TestModel

sealed class TestsLoadingStatus {
    object Default: TestsLoadingStatus()
    object IsLoading: TestsLoadingStatus()
    class Done(val tests: List<TestModel>): TestsLoadingStatus()
    class Exception<T>(val exception: T): TestsLoadingStatus()
}