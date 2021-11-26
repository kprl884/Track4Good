package com.track4good.hackathon.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.track4good.hackathon.domain.entity.ResultData

open class BaseViewModel : ViewModel() {

    val loadingErrorState = MutableLiveData<ResultData<Any>>()

    fun handleTask(task: ResultData<Any>, callback: () -> Unit = {}) {
        loadingErrorState.postValue(task)
        callback.invoke()
    }
}