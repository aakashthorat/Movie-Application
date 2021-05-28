package com.aakash.challenge.movieproject.data

import android.content.Context

import javax.inject.Inject

import com.aakash.challenge.movieproject.data.model.ErrorResponse
import com.aakash.challenge.movieproject.data.model.Movie
import com.aakash.challenge.movieproject.data.model.MoviesResponseModel
import com.aakash.challenge.movieproject.data.network.ApiHelper
import com.aakash.challenge.movieproject.data.prefs.PreferencesHelper
import com.aakash.challenge.movieproject.presentation.internal.di.ApplicationContext
import io.reactivex.Observable
import retrofit2.Response

/**
 * Implementation of manager that implements all forms of data application (network, android preferences)
 */
class AppDataManager @Inject
internal constructor(@param:ApplicationContext private val mContext: Context,
                     private val mPreferencesHelper: PreferencesHelper,
                     private val mApiHelper: ApiHelper) : DataManager {

    override fun getNowPlayingMovies(page: Int): Observable<MoviesResponseModel> {
        return mApiHelper.getNowPlayingMovies(page)
    }

    override fun getMoviesByTitle(query: String, page: Int): Observable<MoviesResponseModel> {
        return mApiHelper.getMoviesByTitle(query, page)
    }

    override fun parseError(response: Response<*>): ErrorResponse? {
        return mApiHelper.parseError(response)
    }

    override fun saveMovies(movies: ArrayList<Movie>) {
        mPreferencesHelper.saveMovies(movies)
    }

    override fun retrieveMovies(): ArrayList<Movie> {
        return mPreferencesHelper.retrieveMovies()
    }

    override fun clearPreferences() {
        mPreferencesHelper.clearPreferences()
    }
}
