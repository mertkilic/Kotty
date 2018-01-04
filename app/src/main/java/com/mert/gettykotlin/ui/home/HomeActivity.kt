package com.mert.gettykotlin.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.GridLayoutManager
import com.mert.gettykotlin.BaseActivity
import com.mert.gettykotlin.R
import com.mert.gettykotlin.data.Item
import com.mert.gettykotlin.data.rest.RestService
import com.mert.gettykotlin.util.OnReachBottomListener
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject
    lateinit var presenter : HomeContract.Presenter

    private var adapter: HomeListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initList()
        presenter.takeView(this)
        presenter.startPresentation()
    }
    private fun initList() {
        adapter = HomeListAdapter()
        home_list.layoutManager = GridLayoutManager(this, 2)
        home_list.addOnScrollListener(object : OnReachBottomListener(home_list, swipe_refresh) {
            override fun onLoadMore(page: Int) {
                presenter.fetchItems(page, RestService.DEFAULT_PAGE_SIZE)
            }
        })
        home_list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    override fun onItemsLoaded(items: List<Item>) {
        adapter!!.add(items)
    }

    override fun onError(t: Throwable) {

    }

    override fun showProgress() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_refresh.isRefreshing = false
    }
}
