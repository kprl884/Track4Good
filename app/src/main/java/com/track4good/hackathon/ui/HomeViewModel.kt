package com.track4good.hackathon.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.track4good.hackathon.common.BaseViewModel
import com.track4good.hackathon.domain.entity.ResultData

class HomeViewModel @ViewModelInject constructor(
) : BaseViewModel() {

    private val _firebaseUserData = MutableLiveData<ResultData<FirebaseAuth>>()
    val firebaseUserData: MutableLiveData<ResultData<FirebaseAuth>>
        get() = _firebaseUserData

}