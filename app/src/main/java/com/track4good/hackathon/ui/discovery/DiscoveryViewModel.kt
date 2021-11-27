package com.track4good.hackathon.ui.discovery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.track4good.hackathon.common.BaseViewModel
import com.track4good.hackathon.data.AdvertDataListFactory
import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


class DiscoveryViewModel @ViewModelInject constructor(
    private val localDetailAdvertListData: AdvertDataListFactory
) : BaseViewModel() {

    private val _advertData = MutableLiveData<ResultData<ArrayList<Advert>>>()
    val advertData: MutableLiveData<ResultData<ArrayList<Advert>>>
        get() = _advertData

    @InternalCoroutinesApi
    fun getAdvert() {
        viewModelScope.launch(Dispatchers.IO) {
            _advertData.postValue(ResultData.Success(localDetailAdvertListData.generateMockAdvertDataFactory()))
        }
    }
}