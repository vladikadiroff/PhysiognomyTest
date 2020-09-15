package ru.vladikadiroff.physiognomy.viewmodels.store

import androidx.lifecycle.ViewModel
import ru.vladikadiroff.physiognomy.PhysiognomyApplication
import ru.vladikadiroff.physiognomy.domain.store.StoreInteractorImpl
import javax.inject.Inject

class StoreDetailViewModel: ViewModel() {

    @Inject
    lateinit var interactor: StoreInteractorImpl

    init {
        PhysiognomyApplication.instance.storeComponent.inject(viewModel = this)
    }

    fun getArgs() = interactor.getArgs()

}