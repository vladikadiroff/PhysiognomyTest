package ru.vladikadiroff.physiognomy

import android.app.Application
import ru.vladikadiroff.physiognomy.di.components.*


class PhysiognomyApplication: Application(){

    companion object{
        lateinit var instance: PhysiognomyApplication private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    // Dagger2

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .build()
    }

    val testsComponent = applicationComponent.testsComponent()
    val storeComponent = applicationComponent.storeComponent()



}