package com.track4good.hackathon.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.track4good.hackathon.common.BaseViewModel
import com.track4good.hackathon.data.AdvertDataDetailFactory
import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val localDetailAdvertData: AdvertDataDetailFactory
) : BaseViewModel() {

    private val _detailAdvertData = MutableLiveData<ResultData<Advert>>()
    val detailAdvertData: MutableLiveData<ResultData<Advert>>
        get() = _detailAdvertData

    @InternalCoroutinesApi
    fun getAdvert(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailAdvertData.postValue(
                ResultData.Success(
                    localDetailAdvertData.generateMockAdvertDataFactory(
                        id
                    )
                )
            )
        }
    }
}