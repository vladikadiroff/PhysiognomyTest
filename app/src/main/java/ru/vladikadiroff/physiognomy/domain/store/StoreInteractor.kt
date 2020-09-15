package ru.vladikadiroff.physiognomy.domain.store

import androidx.lifecycle.MutableLiveData
import ru.vladikadiroff.physiognomy.domain.store.keys.StoreLoadingStatus
import ru.vladikadiroff.physiognomy.domain.store.models.StoreModel

interface StoreInteractor {
    fun loadStore()
    fun safeArgs(args: StoreModel)
    fun getArgs(): StoreModel
    fun getStoreLoadingStatus(): MutableLiveData<StoreLoadingStatus>
}