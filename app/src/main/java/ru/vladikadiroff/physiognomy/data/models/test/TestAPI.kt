package ru.vladikadiroff.physiognomy.data.models.test

import kotlinx.serialization.Serializable

@Serializable
data class TestAPI (
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val icon: String = "",
    val isFree: Boolean = true,
    val marketId: String = "",
    val questions: List<TestQuestionsAPI> = listOf()
)