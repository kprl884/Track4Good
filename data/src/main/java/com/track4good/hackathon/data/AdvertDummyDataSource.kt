package com.track4good.hackathon.data

import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.flow.Flow


interface AdvertDummyDataSource {
    suspend fun getData(): Flow<ResultData<ArrayList<Advert>>>
}