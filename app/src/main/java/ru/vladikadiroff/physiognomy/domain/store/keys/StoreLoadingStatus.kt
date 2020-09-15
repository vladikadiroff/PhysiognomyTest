package ru.vladikadiroff.physiognomy.domain.store.keys

import ru.vladikadiroff.physiognomy.domain.store.models.StoreModel

sealed class StoreLoadingStatus{
    object Default: StoreLoadingStatus()
    object IsLoading: StoreLoadingStatus()
    class Done(val list: List<StoreModel>): StoreLoadingStatus()
    class Exception<T>(val message: T): StoreLoadingStatus()
}