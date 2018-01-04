package com.mert.gettykotlin

import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

/**
 * Created by Mert Kilic on 2.11.2017.
 */

abstract class BaseListAdapter<T> : RecyclerView.Adapter<BaseListAdapter<T>.GenericViewHolder>() {

    protected var items: MutableList<T> = ArrayList()

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.onBindData(items[position])
    }

    protected fun createRoot(parent: ViewGroup, @LayoutRes resId: Int): View {
        return LayoutInflater.from(parent.context).inflate(resId, parent, false)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(items: List<T>) {
        val oldItems = ArrayList(this.items)
        this.items.addAll(items)
        val diffResult = DiffUtil.calculateDiff(ListDiffUtilCallback(oldItems, this.items))
        diffResult.dispatchUpdatesTo(this)
    }

    abstract inner class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun onBindData(item: T)
    }

    private inner class ListDiffUtilCallback(internal var oldList: List<T>, internal var newList: List<T>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return false
        }
    }
}
