package com.example.newsapp.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.newsapp.R


class LoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.item_loading_footer,
            parent,
            false
        ).apply {
            lifecycleOwner = lifecycleOwner
        }
        return LoadStateViewHolder(retry, viewDataBinding)
    }

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}