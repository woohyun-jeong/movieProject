package com.whjung.movieproject.ui

import android.os.Bundle
import com.whjung.movieproject.R
import com.whjung.movieproject.data.PreferenceProvider
import com.whjung.movieproject.databinding.ActivityMainBinding
import com.whjung.movieproject.security.RoutingCheck
import com.whjung.movieproject.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.get

class MainActivity(override val layoutResourceId: Int = R.layout.activity_main) : BaseActivity<ActivityMainBinding>() {

//    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.view = this
//        viewDataBinding.viewModel = viewModel
//        viewDisposables.add(viewModel.compositeDisposable)

//        val preferenceProvider = PreferenceProvider(get(), get())

        if (!RoutingCheck.checkSuperUser()) {

        } else {
            // 루팅된 폰일 때 메세지.
        }

        setContentView(R.layout.activity_main)
    }
}