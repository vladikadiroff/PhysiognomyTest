package ru.vladikadiroff.physiognomy.di.components

import dagger.Component
import ru.vladikadiroff.physiognomy.di.modules.NetworkModule
import ru.vladikadiroff.physiognomy.di.modules.RepositoryModule
import ru.vladikadiroff.physiognomy.di.scope.ApplicationScope

@Component(modules = [ RepositoryModule::class, NetworkModule::class ])
@ApplicationScope
interface ApplicationComponent{
    fun testsComponent(): TestsComponent
    fun storeComponent(): StoreComponent
}


