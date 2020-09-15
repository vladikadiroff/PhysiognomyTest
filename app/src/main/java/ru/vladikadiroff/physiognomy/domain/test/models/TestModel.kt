package ru.vladikadiroff.physiognomy.domain.test.models

data class TestModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val icon: String = "",
    val isFree: Boolean = false,
    val marketId: String ="",
    val questions: List<TestQuestionModel>  = listOf()
)