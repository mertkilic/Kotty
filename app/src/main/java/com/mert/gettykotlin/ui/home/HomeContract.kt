package com.mert.gettykotlin.ui.home

import com.mert.gettykotlin.BasePresenter
import com.mert.gettykotlin.BaseView
import com.mert.gettykotlin.data.Item


/**
 * Created by Mert Kilic on 24.10.2017.
 */

interface HomeContract {
    interface Presenter : BasePresenter<View> {
        fun fetchItems(page: Int, pageSize: Int)
    }

    interface View : BaseView {
        fun onItemsLoaded(items: List<Item>)

        fun onError(t: Throwable)

        fun showProgress()

        fun hideProgress()
    }
}
