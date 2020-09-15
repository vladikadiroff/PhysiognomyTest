package ru.vladikadiroff.physiognomy.domain.test.usecases

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.vladikadiroff.physiognomy.data.RepositoryImpl
import ru.vladikadiroff.physiognomy.domain.test.converters.TestsConverter
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsLoadingStatus
import ru.vladikadiroff.physiognomy.domain.test.models.TestModel
import ru.vladikadiroff.physiognomy.extensions.default
import ru.vladikadiroff.physiognomy.extensions.set

class TestsLoading(
    private val repository: RepositoryImpl,
    private val converter: TestsConverter
) {

    private val loadingStatus = MutableLiveData<TestsLoadingStatus>()
        .default(TestsLoadingStatus.Default)
    private lateinit var disposed: Disposable
    private var tests: List<TestModel> = listOf()

    fun load() {
        loadingStatus.set(TestsLoadingStatus.IsLoading)
        disposed = repository.getTests()
            .map { list -> list.map(converter::fromAPI) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    tests = list
                    loadingStatus.set(TestsLoadingStatus.Done(tests = list))
                },
                { error ->
                    loadingStatus.set(TestsLoadingStatus.Exception(error))
                }
            )
    }

    fun getStatus() = loadingStatus

    fun getTestById(id: Int): TestModel{
        tests.forEach { model ->
            if(model.id == id) return model
        }
        return TestModel()
    }

    fun stop(){
        disposed.dispose()
    }

}