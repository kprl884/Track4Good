package com.track4good.hackathon.ui


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.track4good.hackathon.common.BaseViewModel
import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.usecase.auth.CreateUserAuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val createUseCase: CreateUserAuthUseCase
) : BaseViewModel() {

    private val _firebaseUserData = MutableLiveData<ResultData<Any>>()
    val firebaseUserData: MutableLiveData<ResultData<Any>>
        get() = _firebaseUserData

    @InternalCoroutinesApi
    fun register(userMail: String, userPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _firebaseUserData.postValue(ResultData.Loading())
            createUseCase.invoke(userMail, userPassword).collect { it ->
                handleTask(it) {
                    _firebaseUserData.postValue(it)
                }
            }
        }
    }
}