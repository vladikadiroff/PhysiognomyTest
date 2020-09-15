package ru.vladikadiroff.physiognomy.di.components

import dagger.Subcomponent
import ru.vladikadiroff.physiognomy.di.modules.InteractorStoreModule
import ru.vladikadiroff.physiognomy.di.scope.StoreScope
import ru.vladikadiroff.physiognomy.viewmodels.store.StoreDetailViewModel
import ru.vladikadiroff.physiognomy.viewmodels.store.StoreViewModel


@Subcomponent(modules = [ InteractorStoreModule::class ])
@StoreScope
interface StoreComponent {
    fun inject(viewModel: StoreViewModel)
    fun inject(viewModel: StoreDetailViewModel)
}