package com.example.newsapp.ui.utils

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.BR


class LoadStateViewHolder(
    private val mRetry: () -> Unit,
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {
    val isLoading =  MutableLiveData<Boolean>(true)
    val isError = MutableLiveData<Boolean>(false)

    fun bind(loadState: LoadState) {

        viewDataBinding.setVariable(BR.loaderItem, this)

        isLoading.postValue(loadState is LoadState.Loading)
        isError.postValue(loadState is LoadState.Error)
    }

    fun retry() {
        mRetry()
    }
}
