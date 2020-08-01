package com.whjung.movieproject.rx

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable

class AutoClearedDisposable (
    private val lifecycleOwner: AppCompatActivity,
    private val alwaysClearOnStop: Boolean = true,
    private val compositeDisposableList: MutableList<CompositeDisposable> = mutableListOf()
) : LifecycleObserver
{
    fun add(compositeDisposable: CompositeDisposable) {
        Logger.d("[" + lifecycleOwner.localClassName + "] " + "AutoClearedDisposable add")
        check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
        compositeDisposableList.add(compositeDisposable)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clearUp() {
        if (!alwaysClearOnStop && !lifecycleOwner.isFinishing) {
            return
        }
        Logger.d("[" + lifecycleOwner.localClassName + "] " + "AutoClearedDisposable alwaysClearOnStop ,isFinishing")
        for (compositeDisposable in compositeDisposableList) {
            compositeDisposable.clear()
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf() {
        Logger.d("[" + lifecycleOwner.localClassName + "] " + "AutoClearedDisposable detachSelf")
        for (compositeDisposable in compositeDisposableList) {
            compositeDisposable.clear()
        }
        lifecycleOwner.lifecycle.removeObserver(this)

    }

}