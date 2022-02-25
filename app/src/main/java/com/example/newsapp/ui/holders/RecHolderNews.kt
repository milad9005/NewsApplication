package com.example.newsapp.ui.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.BR
import com.example.newsapp.ui.RecArticle
import com.example.newsapp.ui.utils.Holder
import com.example.newsapp.ui.utils.Visitable

class RecHolderNews  private constructor(private val viewDataBinding: ViewDataBinding) :
    Holder(viewDataBinding) {

    companion object {
        fun from(parent: ViewGroup, viewId: Int): Holder {
            val inflater = LayoutInflater.from(parent.context)
            val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                viewId,
                parent,
                false
            )
            return RecHolderNews(viewDataBinding)
        }


        val diffCallback = object : DiffUtil.ItemCallback<RecArticle>() {
            override fun areItemsTheSame(
                oldItem: RecArticle,
                newItem: RecArticle
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: RecArticle,
                newItem: RecArticle
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun bind(item: Visitable) {
        viewDataBinding.setVariable(BR.item, item)
    }
}