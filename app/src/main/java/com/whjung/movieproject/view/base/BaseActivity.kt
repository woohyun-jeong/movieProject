package com.whjung.movieproject.view.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.whjung.movieproject.rx.AutoClearedDisposable

abstract class BaseActivity<T: ViewDataBinding>:  AppCompatActivity(){

    lateinit var viewDataBinding: T
    internal val viewDisposables = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)
    abstract val layoutResourceId: Int
//    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        viewDataBinding.lifecycleOwner = this
        lifecycle.addObserver(viewDisposables)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    fun addFragment(container : Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commitNow()
    }

//    fun analyticsLogEvent(itemName: String) {
//        val bundle = Bundle()
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
//    }

}