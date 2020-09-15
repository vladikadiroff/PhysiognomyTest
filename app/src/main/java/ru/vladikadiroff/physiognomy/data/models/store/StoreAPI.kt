package ru.vladikadiroff.physiognomy.data.models.store

import kotlinx.serialization.Serializable

@Serializable
data class StoreAPI(
    val id: String = "",
    val name: String = "",
    val icon: String = "",
    val picture: String = "",
    val description: String = "",
    val text: String = "",
    val price: Int = 0,
    val link: String = "")