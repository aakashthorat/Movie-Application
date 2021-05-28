package com.aakash.challenge.movieproject.data.network

import com.aakash.challenge.movieproject.data.model.ErrorResponse
import com.aakash.challenge.movieproject.data.model.MoviesResponseModel
import io.reactivex.Observable
import retrofit2.Response

/**
 * Helper for Retrofit Api
 */
interface ApiHelper {
    fun getNowPlayingMovies(page: Int): Observable<MoviesResponseModel>
    fun getMoviesByTitle(query: String, page: Int): Observable<MoviesResponseModel>
    fun parseError(response: Response<*>): ErrorResponse?
}
