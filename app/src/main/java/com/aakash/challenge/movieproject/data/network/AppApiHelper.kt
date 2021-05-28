package com.aakash.challenge.movieproject.data.network

import java.io.IOException

import javax.inject.Inject

import com.aakash.challenge.movieproject.data.model.ErrorResponse
import com.aakash.challenge.movieproject.data.model.MoviesResponseModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Implementation of helper for Retrofit Api
 */
class AppApiHelper @Inject
internal constructor(private val retrofit: Retrofit) : ApiHelper {

    private val backendApi: BackendApi = retrofit.create(BackendApi::class.java)

    override fun getNowPlayingMovies(page: Int): Observable<MoviesResponseModel> {
        return backendApi.nowPlayingMovies(3, APIConstants.API_KEY, page)
    }

    override fun getMoviesByTitle(query: String, page: Int): Observable<MoviesResponseModel> {
        return backendApi.getMoviesByTitle(3, APIConstants.API_KEY, query, page)
    }

    override fun parseError(response: Response<*>): ErrorResponse {
        val converter = retrofit.responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls(0))
        try {
            return converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            return ErrorResponse()
        }

    }
}
