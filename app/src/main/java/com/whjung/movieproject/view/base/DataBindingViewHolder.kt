package com.hdtel.smarthome.view.base

import androidx.databinding.ViewDataBinding
//import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

open class DataBindingViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
//        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

    fun getBinding(): ViewDataBinding {
        return binding
    }
}