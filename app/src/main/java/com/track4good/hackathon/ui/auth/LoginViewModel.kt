package com.track4good.hackathon.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.track4good.hackathon.common.BaseViewModel
import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.usecase.auth.CreateUserAuthUseCase
import com.track4good.hackathon.domain.usecase.auth.LoginUserAuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginViewModel @ViewModelInject constructor(
    private val loginUserAuthUseCase: LoginUserAuthUseCase
) : BaseViewModel() {

    private val _firebaseUserData = MutableLiveData<ResultData<Any>>()
    val firebaseUserData: MutableLiveData<ResultData<Any>>
        get() = _firebaseUserData

    @InternalCoroutinesApi
    fun login(userMail: String, userPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _firebaseUserData.postValue(ResultData.Loading())
            loginUserAuthUseCase.invoke(userMail, userPassword).collect { it ->
                handleTask(it) {
                    _firebaseUserData.postValue(it)
                }
            }
        }
    }
}