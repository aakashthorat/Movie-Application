package com.aakash.challenge.movieproject.presentation.internal.di.components

import android.app.Application
import android.content.Context
import com.aakash.challenge.movieproject.config.AndroidApplication
import com.aakash.challenge.movieproject.data.DataManager
import com.aakash.challenge.movieproject.presentation.internal.di.ApplicationContext
import com.aakash.challenge.movieproject.presentation.internal.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(app: AndroidApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager
}
