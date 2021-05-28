package com.aakash.challenge.movieproject.config

import android.app.Application
import com.aakash.challenge.movieproject.presentation.internal.di.components.ApplicationComponent
import com.aakash.challenge.movieproject.presentation.internal.di.components.DaggerApplicationComponent
import com.aakash.challenge.movieproject.presentation.internal.di.module.ApplicationModule

class AndroidApplication: Application() {

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }
}