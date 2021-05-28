package com.aakash.challenge.movieproject.presentation.ui.home.search

import com.aakash.challenge.movieproject.data.model.MoviesResponseModel
import com.aakash.challenge.movieproject.presentation.ui.base.MvpView

interface SearchMvpView : MvpView {
    fun showNowPlayingMovies(nowPlayingMovies: MoviesResponseModel)
}
