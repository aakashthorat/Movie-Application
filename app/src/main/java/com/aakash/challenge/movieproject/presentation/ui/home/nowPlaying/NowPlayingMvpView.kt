package com.aakash.challenge.movieproject.presentation.ui.home.nowPlaying

import com.aakash.challenge.movieproject.data.model.MoviesResponseModel
import com.aakash.challenge.movieproject.presentation.ui.base.MvpView

interface NowPlayingMvpView : MvpView {
    fun showNowPlayingMovies(nowPlayingMovies: MoviesResponseModel)
}
