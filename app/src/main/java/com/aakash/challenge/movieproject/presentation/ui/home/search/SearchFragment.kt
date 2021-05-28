package com.aakash.challenge.movieproject.presentation.ui.home.search

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import javax.inject.Inject

import com.aakash.challenge.movieproject.R
import com.aakash.challenge.movieproject.data.model.MoviesResponseModel
import com.aakash.challenge.movieproject.presentation.ui.base.BaseActivity
import com.aakash.challenge.movieproject.presentation.ui.base.BaseFragment
import com.aakash.challenge.movieproject.presentation.ui.custom.MoviesAdapter
import com.aakash.challenge.movieproject.presentation.ui.custom.OnLoadMoreListenerInterface
import com.aakash.challenge.movieproject.presentation.ui.custom.dialogs.GenericDialogOkCancel
import com.aakash.challenge.movieproject.presentation.ui.home.HomeActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class SearchFragment : BaseFragment(), SearchMvpView {

    @BindView(R.id.edt_search)
    lateinit var edtSearch: EditText
    @BindView(R.id.lst_movies)
    lateinit var lstMovies: RecyclerView

    @Inject lateinit var presenter: SearchMvpPresenter<SearchMvpView>

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var mParentActivity: HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParentActivity = baseActivity as HomeActivity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activityComponent.inject(this)
        presenter.onAttach(this)

        val view = inflater!!.inflate(R.layout.fragment_search, container, false)

        ButterKnife.bind(this, view)

        setUp()

        return view
    }

    override fun setUp() {
        setToolbarTitle(R.string.search_title)
        setToolbarStyle(BaseActivity.SEARCH_STYLE)

        moviesAdapter = MoviesAdapter(mParentActivity)

        moviesAdapter.setOnLoadMoreInterfaceListener(object : OnLoadMoreListenerInterface {
            override fun onLoadMore() {
                if (isNetworkConnected)
                    presenter.getMoviesByTitle(edtSearch.text.toString(), moviesAdapter.currentPage)
            }
        })

        lstMovies.adapter = moviesAdapter
        lstMovies.itemAnimator = DefaultItemAnimator()
        lstMovies.layoutManager = LinearLayoutManager(mParentActivity)
    }

    override fun showNowPlayingMovies(nowPlayingMovies: MoviesResponseModel) {
        if (nowPlayingMovies.results!!.size > 0) {
            hideKeyboard()
            moviesAdapter.addMovies(nowPlayingMovies.results!!)
            moviesAdapter.currentPage = nowPlayingMovies.page
            moviesAdapter.setTotalPages(nowPlayingMovies.total_pages)
        } else {
            GenericDialogOkCancel(
                    context,
                    mParentActivity!!.getString(R.string.dialog_title_error),
                    mParentActivity!!.getString(R.string.dialog_title_no_results_message),
                    mParentActivity!!.getString(R.string.dialog_ok_label),
                    null
            ).showDialog()
        }
    }

    @OnClick(R.id.search)
    fun onSearchClick() {
        if (verifyFields() && isNetworkConnected) {
            moviesAdapter.clearMovies()
            presenter.getMoviesByTitle(edtSearch.text.toString(), moviesAdapter.currentPage)
        }
    }

    private fun verifyFields(): Boolean {
        if (edtSearch.text.toString().trim { it <= ' ' }.isEmpty()) {
            GenericDialogOkCancel(
                    context,
                    mParentActivity!!.getString(R.string.dialog_title_error),
                    mParentActivity!!.getString(R.string.dialog_title_mandatory_search_filed_message),
                    mParentActivity!!.getString(R.string.dialog_ok_label), null
            ).showDialog()
            return false
        }
        return true
    }

    companion object {

        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
