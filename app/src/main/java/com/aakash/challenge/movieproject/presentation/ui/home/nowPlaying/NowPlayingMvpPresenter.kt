package com.aakash.challenge.movieproject.presentation.ui.home.nowPlaying

import com.aakash.challenge.movieproject.data.model.Movie
import com.aakash.challenge.movieproject.presentation.internal.di.PerActivity
import com.aakash.challenge.movieproject.presentation.ui.base.MvpPresenter

@PerActivity
interface NowPlayingMvpPresenter<V : NowPlayingMvpView> : MvpPresenter<V> {
    val moviesFromPreference: ArrayList<Movie>
    fun getNowPlayingMovies(averageVote: Double, page: Int)
    fun saveMoviesOnPreferences(results: ArrayList<Movie>)
}
