package ru.vladikadiroff.physiognomy.domain.store

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.vladikadiroff.physiognomy.data.RepositoryImpl
import ru.vladikadiroff.physiognomy.domain.store.converters.StoreConverter
import ru.vladikadiroff.physiognomy.domain.store.keys.StoreLoadingStatus
import ru.vladikadiroff.physiognomy.domain.store.models.StoreModel
import ru.vladikadiroff.physiognomy.extensions.default
import ru.vladikadiroff.physiognomy.extensions.set

class StoreInteractorImpl(
    private val repository: RepositoryImpl,
    private val converter: StoreConverter
): StoreInteractor {

    private var args = StoreModel()
    private val loadingStatus = MutableLiveData<StoreLoadingStatus>().default(StoreLoadingStatus.Default)

    override fun loadStore() {
        loadingStatus.set(StoreLoadingStatus.IsLoading)
        val dispose = repository.getStoreItems()
            .map { list -> list.map(converter::fromApi) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    loadingStatus.set(StoreLoadingStatus.Done(list))
                },
                { error ->
                    loadingStatus.set(StoreLoadingStatus.Exception(error))
                }
            )
    }

    override fun getStoreLoadingStatus(): MutableLiveData<StoreLoadingStatus> {
        return loadingStatus
    }

    override fun safeArgs(args: StoreModel) {
        this.args = args
    }

    override fun getArgs(): StoreModel {
        return args
    }
}