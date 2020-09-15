package ru.vladikadiroff.physiognomy.di.modules

import dagger.Module
import dagger.Provides
import ru.vladikadiroff.physiognomy.data.RepositoryImpl
import ru.vladikadiroff.physiognomy.di.scope.StoreScope
import ru.vladikadiroff.physiognomy.domain.store.StoreInteractorImpl
import ru.vladikadiroff.physiognomy.domain.store.converters.StoreConverter

@Module
class InteractorStoreModule {

    @Provides
    @StoreScope
    fun provideInteractorShop(repository: RepositoryImpl) =
        StoreInteractorImpl(repository, provideShopConverter())

    @Provides
    fun provideShopConverter() = StoreConverter()

}