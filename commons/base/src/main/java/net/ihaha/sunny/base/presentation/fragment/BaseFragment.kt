package net.ihaha.sunny.base.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.rasalexman.coroutinesmanager.ICoroutinesManager
import com.rasalexman.coroutinesmanager.launchOnUITryCatch
import io.reactivex.disposables.Disposable
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.data.dto.SEvent
import net.ihaha.sunny.base.data.dto.SResult
import net.ihaha.sunny.base.utils.extensions.*
import net.ihaha.sunny.base.utils.lifecycle.LifeCycleObserverUtils
import net.ihaha.sunny.base.utils.typealiases.UnitHandler
import net.ihaha.sunny.base.viewModels.IBaseViewModel

abstract class BaseFragment<out VM : IBaseViewModel> : Fragment(), ICoroutinesManager {

    //region variable
    abstract val layoutId: Int
    abstract fun initBinding()

    protected open val contentViewLayout: View? = null
    protected open val loadingViewLayout: View? = null
    protected open val viewModel: VM? = null

    protected open fun initLayout() = Unit

    private val autoLifeCycleObserver by lazy { LifeCycleObserverUtils(lifecycle) }
    //endregion

    //region override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoLifeCycleObserver.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutId, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    override fun onDestroyView() {
        context?.closeAlert()
        super.onDestroyView()
    }

    //endregion

    //region method

    /**
     * Show alert dialog for [SResult.ErrorResult.Alert]
     */
    protected open fun showAlertDialog(
        message: Any,
        okTitle: Int = R.string.title_try_again,
        okHandler: UnitHandler? = null
    ) {
        hideKeyboard()
        hideLoading()
        alert(message = message, okTitle = okTitle, okHandler = okHandler)
    }

    /**
     * Show toast message for [SResult.ErrorResult.Error]
     */
    protected open fun showToast(message: Any, interval: Int = Toast.LENGTH_SHORT) {
        hideKeyboard()
        hideLoading()
        toast(message, interval)
    }

    /**
     * Show loading state for [SResult.Loading]
     */
    open fun showLoading() = launchOnUITryCatch(tryBlock = {
        hideKeyboard()
        loadingViewLayout?.show()
        contentViewLayout?.hide()
    }, catchBlock = {
        log { "Cannot perform action: `showLoading` with $this@BaseFragment. Error ${it.message}" }
    })

    open fun hideLoading() = launchOnUITryCatch(tryBlock = {
        hideKeyboard()
        loadingViewLayout?.hide()
        contentViewLayout?.show()
    }, catchBlock = {
        log { "Cannot perform action: `hideLoading` with $this@BaseFragment. Error ${it.message}" }
    })

    /**
     * Base [SResult] handle function
     */
    protected open fun onResultHandler(result: SResult<*>) {
        if (result.isHandled) return
        result.handle()

        when (result) {
            is SResult.Success -> hideLoading()
            is SResult.Loading -> showLoading()
            is SResult.ErrorResult -> {
                result.getMessage()?.let {
                    if (result is SResult.ErrorResult.Error) {
                        showToast(it)
                    } else if (result is SResult.ErrorResult.Alert) {
                        showAlertDialog(it)
                    }
                }
            }
        }
    }

    /**
     * Process [SEvent] to view model
     */
    protected open fun processViewEvent(viewEvent: SEvent) {
        this.viewModel?.processViewEvent(viewEvent)
    }

    fun addDisposable(disposable: Disposable) {
        autoLifeCycleObserver.addDisposable(disposable)
    }


    //endregion
}

