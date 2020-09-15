package ru.vladikadiroff.physiognomy.viewmodels.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vladikadiroff.physiognomy.PhysiognomyApplication
import ru.vladikadiroff.physiognomy.domain.store.StoreInteractorImpl
import ru.vladikadiroff.physiognomy.domain.store.keys.StoreLoadingStatus
import ru.vladikadiroff.physiognomy.domain.store.models.StoreModel
import javax.inject.Inject

class StoreViewModel: ViewModel() {

    @Inject
    lateinit var interactor: StoreInteractorImpl

    val state: MutableLiveData<StoreLoadingStatus>

    init {
        PhysiognomyApplication.instance.storeComponent.inject(viewModel = this)
        state = interactor.getStoreLoadingStatus()
        interactor.loadStore()
    }

    fun onRetryGetStore(){
        interactor.loadStore()
    }

    fun safeArgs(args: StoreModel){
        interactor.safeArgs(args)
    }

}