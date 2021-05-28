package com.aakash.challenge.movieproject.data.prefs;

import com.aakash.challenge.movieproject.data.model.Movie

interface PreferencesHelper {
    fun saveMovies(movies: ArrayList<Movie>)
    fun retrieveMovies(): ArrayList<Movie>
    fun clearPreferences()
}
