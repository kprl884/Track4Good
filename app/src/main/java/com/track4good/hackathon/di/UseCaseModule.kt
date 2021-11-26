package com.track4good.hackathon.di


import com.track4good.hackathon.domain.repository.AuthRepository
import com.track4good.hackathon.domain.usecase.auth.CreateUserAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object UseCaseModule {
    @Provides
    fun providesRegisterUseCase(repository: AuthRepository): CreateUserAuthUseCase {
        return CreateUserAuthUseCase(
            repository
        )
    }
}