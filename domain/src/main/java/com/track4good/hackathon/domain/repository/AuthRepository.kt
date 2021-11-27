package com.track4good.hackathon.domain.repository

import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.entity.UserType
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(userMail: String, userPassword: String): Flow<ResultData<Unit>>
    suspend fun initializeUserToFirestore(fullName: String, birthDate: String, hesCode: String, userType: String, userMail: String, userPassword: String): Flow<ResultData<Unit>>
    suspend fun login(userMail: String, userPassword: String): Flow<ResultData<Unit>>
}