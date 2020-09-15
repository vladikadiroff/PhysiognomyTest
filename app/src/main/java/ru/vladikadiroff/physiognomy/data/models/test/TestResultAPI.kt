package ru.vladikadiroff.physiognomy.data.models.test

import kotlinx.serialization.Serializable

@Serializable
data class TestResultAPI(val name: String = "", val text: String = "")