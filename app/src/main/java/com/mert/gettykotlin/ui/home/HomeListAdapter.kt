package com.mert.gettykotlin.ui.home

import android.view.View
import android.view.ViewGroup
import com.mert.gettykotlin.BaseListAdapter
import com.mert.gettykotlin.R
import com.mert.gettykotlin.data.Item


/**
 * Created by Mert Kilic on 2.11.2017.
 */

class HomeListAdapter : BaseListAdapter<Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return HomeViewHolder(createRoot(parent, R.layout.item_home))
    }

    inner class HomeViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun onBindData(item: Item) {

        }
    }


}
