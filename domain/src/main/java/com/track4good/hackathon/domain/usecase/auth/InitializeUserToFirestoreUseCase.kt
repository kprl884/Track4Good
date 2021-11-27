package com.track4good.hackathon.domain.usecase.auth

import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.entity.UserType
import com.track4good.hackathon.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InitializeUserToFirestoreUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(fullName: String, birthDate: String, hesCode: String, userType: String, userMail: String, userPassword: String): Flow<ResultData<Any>> {
        return repository.initializeUserToFirestore(fullName, birthDate, hesCode, userType, userMail, userPassword)
    }
}