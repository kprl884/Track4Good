package com.track4good.hackathon.domain.repository

import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.flow.Flow


interface AdvertRepository {
    suspend fun getData(): Flow<ResultData<ArrayList<Advert>>>

}