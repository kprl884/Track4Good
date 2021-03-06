package com.track4good.hackathon.data.repository

import com.track4good.hackathon.data.AuthRemoteDataSource
import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.entity.UserType
import com.track4good.hackathon.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthRemoteDataSource) :
    AuthRepository {
    override suspend fun register(userMail: String, userPassword: String): Flow<ResultData<Unit>> {
        return dataSource.register(userMail, userPassword)
    }

    override suspend fun initializeUserToFirestore(
        fullName: String,
        birthDate: String,
        hesCode: String,
        userType: String,
        userMail: String,
        userPassword: String
    ): Flow<ResultData<Unit>> {
        return dataSource.initializeUserToFirestore(fullName, birthDate, hesCode, userType, userMail, userPassword)
    }

    override suspend fun login(userMail: String, userPassword: String): Flow<ResultData<Unit>> {
        return dataSource.login(userMail, userPassword)
    }
}