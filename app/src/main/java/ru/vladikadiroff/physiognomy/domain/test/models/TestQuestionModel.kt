package ru.vladikadiroff.physiognomy.domain.test.models

data class TestQuestionModel (
    val name: String = "",
    val description: String = "",
    val listVariants: List<TestQuestionVariantModel> = emptyList()
)