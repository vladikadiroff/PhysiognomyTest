package ru.vladikadiroff.physiognomy.domain.store.models

data class StoreModel(
    val id: String = "",
    val name: String = "",
    val icon: String = "",
    val picture: String = "",
    val description: String = "",
    val text: String = "",
    val price: Int = 0,
    val link: String = ""
)