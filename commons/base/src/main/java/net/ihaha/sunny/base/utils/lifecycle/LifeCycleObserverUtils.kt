package net.ihaha.sunny.base.utils.lifecycle

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import net.ihaha.sunny.base.presentation.application.currentClassName

/**
 * Created by mkwon on 02/04/2019
 */
class LifeCycleObserverUtils(private val lifecycle: Lifecycle) : LifecycleObserver {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var className: String? = null

    fun init(activity: Activity) {
        lifecycle.addObserver(this)
        className = activity::class.java.simpleName
    }

    fun init(fragment: Fragment) {
        lifecycle.addObserver(this)
        className = fragment::class.java.simpleName
    }

    fun addDisposable(disposable: Disposable) {
        check(lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
        compositeDisposable += disposable
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() = logInfo("onCreate()")

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() = logInfo("onStart()")

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        logInfo("onResume()")
        currentClassName = className
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() = logInfo("onPause()")

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() = logInfo("onStop()")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        logInfo("onDestroy()")
        compositeDisposable.clear()
        lifecycle.removeObserver(this)
    }

    private fun logInfo(lifecycleName: String) {
        // TODO: Logger
    }

}