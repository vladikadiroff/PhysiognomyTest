package ru.vladikadiroff.physiognomy.domain.test.usecases

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.vladikadiroff.physiognomy.data.RepositoryImpl
import ru.vladikadiroff.physiognomy.domain.test.converters.TestResultConverter
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsResultLoadingStatus
import ru.vladikadiroff.physiognomy.extensions.default
import ru.vladikadiroff.physiognomy.extensions.set

class TestResultLoading (
    private val repository: RepositoryImpl,
    private val converter: TestResultConverter
) {

    private val loadingStatus = MutableLiveData<TestsResultLoadingStatus>()
        .default(TestsResultLoadingStatus.Default)
    private lateinit var disposed: Disposable

    fun load(answers: String) {
        loadingStatus.set(TestsResultLoadingStatus.IsLoading)
        disposed = repository.getTestResult(answers = answers)
            .map { list -> list.map(converter::fromApi) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Handler().postDelayed({
                        loadingStatus.set(TestsResultLoadingStatus.Done(result))
                    }, 5000)

                },
                { error ->
                    loadingStatus.set(TestsResultLoadingStatus.Exception(error))
                }
            )
    }

    fun getStatus() = loadingStatus

    fun stop(){
        disposed.dispose()
    }
}