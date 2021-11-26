package com.track4good.hackathon.data

import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    suspend fun register(userMail: String, userPassword: String): Flow<ResultData<Unit>>
}