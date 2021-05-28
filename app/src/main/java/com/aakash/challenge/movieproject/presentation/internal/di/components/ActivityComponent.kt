package com.aakash.challenge.movieproject.presentation.internal.di.components

import com.aakash.challenge.movieproject.presentation.internal.di.PerActivity
import com.aakash.challenge.movieproject.presentation.internal.di.module.ActivityModule
import com.aakash.challenge.movieproject.presentation.ui.home.HomeActivity
import com.aakash.challenge.movieproject.presentation.ui.home.nowPlaying.NowPlayingFragment
import com.aakash.challenge.movieproject.presentation.ui.home.search.SearchFragment
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: HomeActivity)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: NowPlayingFragment)
}