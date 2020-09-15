package ru.vladikadiroff.physiognomy.di.modules

import dagger.Module
import dagger.Provides
import ru.vladikadiroff.physiognomy.data.RepositoryImpl
import ru.vladikadiroff.physiognomy.data.api.Service
import ru.vladikadiroff.physiognomy.di.scope.ApplicationScope

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideRepository(service: Service) = RepositoryImpl(service = service)

}