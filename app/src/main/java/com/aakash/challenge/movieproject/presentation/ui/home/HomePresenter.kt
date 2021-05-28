package com.aakash.challenge.movieproject.presentation.ui.home

import com.aakash.challenge.movieproject.data.DataManager
import com.aakash.challenge.movieproject.presentation.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter<V : HomeMvpView> @Inject
internal constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), HomeMvpPresenter<V> {

    override fun clearMoviesFromPreferences() {
        dataManager.clearPreferences()
    }
}
