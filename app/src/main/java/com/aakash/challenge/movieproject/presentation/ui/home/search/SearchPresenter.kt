package com.aakash.challenge.movieproject.presentation.ui.home.search

import javax.inject.Inject

import com.aakash.challenge.movieproject.data.DataManager
import com.aakash.challenge.movieproject.data.network.APIConstants
import com.aakash.challenge.movieproject.presentation.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter<V : SearchMvpView> @Inject
internal constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), SearchMvpPresenter<V> {

    override fun getMoviesByTitle(title: String, page: Int) {
        var title = title
        if (page == APIConstants.INITIAL_PAGINATION_INDEX)
            mvpView!!.showLoading()

        title = title.replace(" ", "+")

        compositeDisposable.add(dataManager.getMoviesByTitle(title, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { nowPlayingMovies ->
                            val view = mvpView
                            view!!.hideLoading()

                            mvpView!!.showNowPlayingMovies(nowPlayingMovies)
                        }
                ) { throwable ->
                    val view = mvpView
                    view!!.hideLoading()

                    handlerThrowableError(view, throwable)
                }
        )
    }
}
