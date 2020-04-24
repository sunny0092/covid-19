package net.ihaha.sunny.base.presentation.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.Disposable
import net.ihaha.sunny.base.utils.extensions.hideKeyboard
import net.ihaha.sunny.base.utils.lifecycle.LifeCycleObserverUtils
import org.koin.android.ext.android.inject


abstract class BaseActivity<T: ViewDataBinding>() : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected lateinit var dataBinding: T

    private val sharedPreferences: SharedPreferences by inject()

    protected val autoLifeCycleObserver by lazy { LifeCycleObserverUtils(lifecycle) }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoLifeCycleObserver.init(this)
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        dataBinding.lifecycleOwner = this
    }

    override fun finish() {
        hideKeyboard()
        super.finish()
    }

    fun addDisposable(disposable: Disposable) {
        autoLifeCycleObserver.addDisposable(disposable)
    }
}