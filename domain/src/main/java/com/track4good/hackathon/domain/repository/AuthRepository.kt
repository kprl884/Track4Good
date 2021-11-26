package com.track4good.hackathon.domain.repository

import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(userMail: String, userPassword: String): Flow<ResultData<Unit>>
}