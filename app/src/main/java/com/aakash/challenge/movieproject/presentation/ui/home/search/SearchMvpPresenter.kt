package com.aakash.challenge.movieproject.presentation.ui.home.search

import com.aakash.challenge.movieproject.presentation.ui.base.MvpPresenter
import javax.inject.Singleton

@Singleton
interface SearchMvpPresenter<V : SearchMvpView> : MvpPresenter<V> {
    fun getMoviesByTitle(title: String, page: Int)
}
