package com.example.newsapp.ui.utils

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class Holder
    (private val viewDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    abstract fun bind(item: Visitable)
}