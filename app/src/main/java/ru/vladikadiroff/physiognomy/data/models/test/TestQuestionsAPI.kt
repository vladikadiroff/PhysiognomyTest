package ru.vladikadiroff.physiognomy.data.models.test

import kotlinx.serialization.*

@Serializable
data class TestQuestionsAPI(
    val name: String = "",
    val description: String = "",
    val facial_features: List<TestQuestionVariantAPI> = emptyList()) {

    @Serializable
    data class TestQuestionVariantAPI(
        val name: String = "",
        val image: String  = "")

}








