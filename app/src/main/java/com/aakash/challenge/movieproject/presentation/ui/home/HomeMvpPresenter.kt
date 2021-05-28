package com.aakash.challenge.movieproject.presentation.ui.home

import com.aakash.challenge.movieproject.presentation.internal.di.PerActivity
import com.aakash.challenge.movieproject.presentation.ui.base.MvpPresenter

@PerActivity
interface HomeMvpPresenter<V : HomeMvpView> : MvpPresenter<V> {
    fun clearMoviesFromPreferences()
}
