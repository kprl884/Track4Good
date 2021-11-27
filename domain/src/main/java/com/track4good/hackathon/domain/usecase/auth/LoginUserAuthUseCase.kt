package com.track4good.hackathon.domain.usecase.auth

import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LoginUserAuthUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(userMail: String, userPassword: String): Flow<ResultData<Any>> {
        return repository.login(userMail, userPassword)
    }
}