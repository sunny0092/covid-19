package net.ihaha.sunny.base.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import net.ihaha.sunny.base.data.dto.SEvent
import net.ihaha.sunny.base.data.dto.SResult

interface IBaseViewModel {

    val eventLiveData: MutableLiveData<SEvent>
    val anyLiveData: LiveData<*>?
    val resultLiveData: LiveData<*>?
    val errorLiveData: MutableLiveData<SResult<*>>

    fun <T : Any> addToSaveState(key: String, value: T)
    fun <T : Any> getFromSaveState(key: String): T?
}