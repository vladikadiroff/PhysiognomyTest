package ru.vladikadiroff.physiognomy.di.components

import dagger.Subcomponent
import ru.vladikadiroff.physiognomy.di.modules.InteractorTestsModule
import ru.vladikadiroff.physiognomy.di.scope.TestsScope
import ru.vladikadiroff.physiognomy.viewmodels.test.TestsViewModel
import ru.vladikadiroff.physiognomy.viewmodels.test.TestResultViewModel
import ru.vladikadiroff.physiognomy.viewmodels.test.TestViewModel

@Subcomponent(modules = [ InteractorTestsModule::class ])
@TestsScope
interface TestsComponent{
    fun inject(viewModel: TestsViewModel)
    fun inject(viewModel: TestViewModel)
    fun inject(viewModel: TestResultViewModel)

}