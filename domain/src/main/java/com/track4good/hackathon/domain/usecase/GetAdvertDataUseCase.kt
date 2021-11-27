package com.track4good.hackathon.domain.usecase

import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.repository.AdvertRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAdvertDataUseCase @Inject constructor(private val repository: AdvertRepository) {
    suspend operator fun invoke(): Flow<ResultData<ArrayList<Advert>>> {
        return repository.getData()
    }
}