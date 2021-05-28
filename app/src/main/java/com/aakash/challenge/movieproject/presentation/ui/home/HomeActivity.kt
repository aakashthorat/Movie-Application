package com.aakash.challenge.movieproject.presentation.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem

import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions

import java.util.ArrayList

import javax.inject.Inject

import com.aakash.challenge.movieproject.R
import com.aakash.challenge.movieproject.presentation.ui.base.BaseActivity
import com.aakash.challenge.movieproject.presentation.ui.base.BaseFragment
import com.aakash.challenge.movieproject.presentation.ui.base.FragManagerListerner
import com.aakash.challenge.movieproject.presentation.ui.home.nowPlaying.NowPlayingFragment
import com.aakash.challenge.movieproject.presentation.ui.home.search.SearchFragment

open class HomeActivity : BaseActivity(), FragManagerListerner, HomeMvpView {

    @Inject lateinit var presenter: HomeMvpPresenter<HomeMvpView>

    private var fragments: MutableList<Fragment>? = null
    private var builder: FragNavController.Builder? = null
    private var fragNavController: FragNavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        component.inject(this)
        presenter.onAttach(this)

        fragments = ArrayList(1)
        builder = FragNavController.newBuilder(savedInstanceState, supportFragmentManager, R.id.container)
        setUp()
    }

    override fun setUp() {
        fragments!!.add(NowPlayingFragment.newInstance())

        builder!!.rootFragments(fragments!!)
        fragNavController = builder!!.build()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (fragNavController != null) {
            fragNavController!!.onSaveInstanceState(outState)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> popFragment()
            R.id.search -> pushFragment(SearchFragment.newInstance())
        }
        return false
    }

    override fun onBackPressed() {
        popFragment()
    }

    override fun pushFragment(fragment: BaseFragment) {
        val transactionOptionsBuilder = FragNavTransactionOptions.newBuilder()
        val transactionOptions = transactionOptionsBuilder.build()
        fragNavController!!.pushFragment(fragment, transactionOptions)
    }

    override fun popFragment() {
        val transactionOptionsBuilder = FragNavTransactionOptions.newBuilder()
        val transactionOptions = transactionOptionsBuilder.build()

        if (fragNavController!!.currentStack!!.size > 1) {
            fragNavController!!.popFragment(transactionOptions)
        } else {
            // Clear the movies list from cache. The list stays on cache only on execution time
            presenter.clearMoviesFromPreferences()
            finish()
        }
    }
}
