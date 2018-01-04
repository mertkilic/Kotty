package com.mert.gettykotlin.util

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * Created by Mert Kilic on 2.11.2017.
 */

abstract class OnReachBottomListener(recyclerView: RecyclerView,
                                     private val dataLoading: SwipeRefreshLayout?) : RecyclerView.OnScrollListener() {

    private val layoutManager: RecyclerView.LayoutManager = recyclerView.layoutManager
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        // bail out if scrolling upward or already loading data
        val firstVisibleItem = fetchFirstVisibleItemPosition()
        if (dy < 0 || dataLoading != null && dataLoading.isRefreshing || firstVisibleItem == -1) return

        val visibleItemCount = recyclerView!!.childCount
        val totalItemCount = layoutManager.itemCount

        if (totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD) {
            currentPage++
            onLoadMore(currentPage)
        }
    }

    private fun fetchFirstVisibleItemPosition(): Int {
        if (layoutManager is LinearLayoutManager) {
            return LinearLayoutManager::class.java.cast(layoutManager).findFirstVisibleItemPosition()
        } else if (layoutManager is StaggeredGridLayoutManager) {
            val manager = StaggeredGridLayoutManager::class.java.cast(layoutManager)
            val result = manager.findFirstVisibleItemPositions(null)
            if (result != null && result.size > 0) {
                return result[0]
            }
        }
        return -1
    }

    abstract fun onLoadMore(page: Int)

    companion object {

        private val VISIBLE_THRESHOLD = 5
    }
}
