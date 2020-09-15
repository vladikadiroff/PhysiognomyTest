package ru.vladikadiroff.physiognomy.di.modules

import dagger.Module
import dagger.Provides
import ru.vladikadiroff.physiognomy.domain.test.TestsInteractorImpl
import ru.vladikadiroff.physiognomy.data.RepositoryImpl
import ru.vladikadiroff.physiognomy.di.scope.TestsScope
import ru.vladikadiroff.physiognomy.domain.test.converters.TestResultConverter
import ru.vladikadiroff.physiognomy.domain.test.converters.TestsConverter
import ru.vladikadiroff.physiognomy.domain.test.usecases.TestAnswersCounter
import ru.vladikadiroff.physiognomy.domain.test.usecases.TestResultLoading
import ru.vladikadiroff.physiognomy.domain.test.usecases.TestsLoading

@Module
class InteractorTestsModule {

    @Provides
    @TestsScope
    fun provideInteractor(repository: RepositoryImpl): TestsInteractorImpl {
        return TestsInteractorImpl(
            provideTestsUseCase(repository),
            provideTestResultUseCase(repository),
            provideAnswersCounter()
        )
    }

    @Provides
    fun provideTestsConverter() = TestsConverter()

    @Provides
    fun provideTestResultConverter() = TestResultConverter()

    @Provides
    @TestsScope
    fun provideAnswersCounter() = TestAnswersCounter()

    @Provides
    @TestsScope
    fun provideTestsUseCase(repository: RepositoryImpl): TestsLoading {
        return TestsLoading(repository, provideTestsConverter())
    }

    @Provides
    @TestsScope
    fun provideTestResultUseCase(repository: RepositoryImpl): TestResultLoading {
        return TestResultLoading(repository, provideTestResultConverter())
    }

}