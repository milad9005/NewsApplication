package com.example.newsapp.ui.utils

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class GenericPagingDataAdapter<T : Visitable, VH : Holder>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val holderBuilder: (ViewGroup, Int) -> VH
) :
    PagingDataAdapter<T, VH>(diffCallback) {
    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return holderBuilder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.type() ?: -1
    }
}