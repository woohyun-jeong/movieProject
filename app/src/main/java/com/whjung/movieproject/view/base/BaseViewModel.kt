package com.whjung.movieproject.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel(): ViewModel() {

    val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun runOnIoScheduler(func: () -> Unit): Disposable = Completable.fromCallable(func).subscribeOn(
        Schedulers.io()).subscribe()
}