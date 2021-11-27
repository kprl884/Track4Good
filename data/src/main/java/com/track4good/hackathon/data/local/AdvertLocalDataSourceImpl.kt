package com.track4good.hackathon.data.local


import com.track4good.hackathon.data.AdvertDummyDataSource
import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowViaChannel
import javax.inject.Inject

class AdvertLocalDataSourceImpl @Inject constructor() :
    AdvertDummyDataSource {
    override suspend fun getData(): Flow<ResultData<ArrayList<Advert>>> {
        return flowViaChannel { flowChannel ->
            //flowChannel.sendBlocking(ResultData.Success(AdvertDataFactory().generateMockAdvertDataFactory()))
        }
    }
}