package com.aakash.challenge.movieproject.presentation.internal.di.module

import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import android.app.Activity
import android.content.Context
import dagger.Module
import com.aakash.challenge.movieproject.presentation.ui.home.search.SearchMvpView
import com.aakash.challenge.movieproject.presentation.ui.home.search.SearchPresenter
import com.aakash.challenge.movieproject.presentation.ui.home.search.SearchMvpPresenter
import com.aakash.challenge.movieproject.presentation.ui.home.nowPlaying.NowPlayingMvpView
import com.aakash.challenge.movieproject.presentation.ui.home.nowPlaying.NowPlayingPresenter
import com.aakash.challenge.movieproject.presentation.ui.home.nowPlaying.NowPlayingMvpPresenter
import com.aakash.challenge.movieproject.presentation.ui.home.HomeMvpView
import com.aakash.challenge.movieproject.presentation.ui.home.HomePresenter
import com.aakash.challenge.movieproject.presentation.ui.home.HomeMvpPresenter
import com.aakash.challenge.movieproject.presentation.internal.di.ActivityContext
import com.aakash.challenge.movieproject.presentation.internal.di.PerActivity

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @PerActivity
    fun provideHomePresenter(presenter: HomePresenter<HomeMvpView>): HomeMvpPresenter<HomeMvpView> {
        return presenter
    }

    @Provides
    @PerActivity
    fun provideNowPlayingPresenter(presenter: NowPlayingPresenter<NowPlayingMvpView>): NowPlayingMvpPresenter<NowPlayingMvpView> {
        return presenter
    }

    @Provides
    @PerActivity
    fun provideSearchPresenter(presenter: SearchPresenter<SearchMvpView>): SearchMvpPresenter<SearchMvpView> {
        return presenter
    }
}
