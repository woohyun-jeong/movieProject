package com.whjung.movieproject.view.base

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseDialog<T : ViewDataBinding>(context: Context, @StyleRes themeResId: Int) : Dialog(context, themeResId) {

    lateinit var viewDataBinding: T

    protected fun setBinding(@LayoutRes layoutId: Int) {
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
        setContentView(viewDataBinding.root)
    }

    protected fun getBinding(): T = viewDataBinding
}