package com.track4good.hackathon.data.repository

import com.track4good.hackathon.data.AdvertDummyDataSource
import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.repository.AdvertRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdvertRepositoryImpl @Inject constructor(private val dataSource: AdvertDummyDataSource) :
    AdvertRepository {
    override suspend fun getData(): Flow<ResultData<ArrayList<Advert>>> {
        return dataSource.getData()
    }


}