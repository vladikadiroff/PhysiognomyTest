package ru.vladikadiroff.physiognomy.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import ru.vladikadiroff.physiognomy.data.api.Service
import ru.vladikadiroff.physiognomy.di.scope.ApplicationScope

@Module
class NetworkModule {

    private val BASE_URL = "http://update.3dn.ru/physiognomy_app/"
    private val converterFactory = Json.asConverterFactory("application/json".toMediaType())

    @Provides
    @ApplicationScope
    fun service(retrofit: Retrofit) = retrofit.create(Service::class.java)

    @Provides
    @ApplicationScope
    fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()


    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

}