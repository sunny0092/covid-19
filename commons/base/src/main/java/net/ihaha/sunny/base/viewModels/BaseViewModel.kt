package net.ihaha.sunny.base.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.rasalexman.coroutinesmanager.ICoroutinesManager
import com.rasalexman.coroutinesmanager.SuspendCatch
import com.rasalexman.coroutinesmanager.SuspendTry
import com.rasalexman.coroutinesmanager.launchOnUITryCatch
import net.ihaha.sunny.base.data.dto.SEvent
import net.ihaha.sunny.base.data.dto.SResult
import net.ihaha.sunny.base.utils.extensions.errorResult

open class BaseViewModel : ViewModel(), IBaseViewModel {

    override val eventLiveData = MutableLiveData<SEvent>()

    override val anyLiveData: LiveData<*>? = null
    override val resultLiveData: LiveData<*>? = null
    override val errorLiveData = MutableLiveData<SResult<*>>()

    protected open val defaultCatchBlock: SuspendCatch<Unit> = {
        errorLiveData.value = errorResult(message = it.message.orEmpty(), exception = it)
    }

    /**
     * Base Function to add to ViewStateHandler Some Values by key [String]
     */
    override fun <T : Any> addToSaveState(key: String, value: T) = Unit

    /**
     * Base Function fro getting value from ViewStateHandler by key [String]
     */
    override fun <T : Any> getFromSaveState(key: String): T? = null

    /**
     *
     */
    fun ICoroutinesManager.launchOnUIDefault(tryBlock: SuspendTry<Unit>) =
        launchOnUITryCatch(tryBlock, defaultCatchBlock)

}
