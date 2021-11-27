package com.track4good.hackathon.di

import com.google.firebase.auth.FirebaseAuth
import com.track4good.hackathon.data.AdvertDataDetailFactory
import com.track4good.hackathon.data.AdvertDataListFactory
import com.track4good.hackathon.data.AdvertDummyDataSource
import com.track4good.hackathon.data.AuthRemoteDataSource
import com.track4good.hackathon.data.local.AdvertLocalDataSourceImpl
import com.track4good.hackathon.data.remote.AuthRemoteDataSourceImpl
import com.track4good.hackathon.data.repository.AdvertRepositoryImpl
import com.track4good.hackathon.data.repository.AuthRepositoryImpl
import com.track4good.hackathon.domain.repository.AdvertRepository
import com.track4good.hackathon.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideFirebaseUser(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(
        remoteDataSource: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(
            remoteDataSource
        )
    }

    @Provides
    fun providesAuthRemoteDataSource(): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(provideFirebaseUser())
    }

    @Provides
    fun providesDummyDataRepository(
        localDataSource: AdvertDummyDataSource
    ): AdvertRepository {
        return AdvertRepositoryImpl(
            localDataSource
        )
    }

    @Provides
    fun providesDummyDataRemoteDataSource(): AdvertDummyDataSource {
        return AdvertLocalDataSourceImpl()
    }

    @Provides
    fun detailAdvertData(): AdvertDataDetailFactory {
        return AdvertDataDetailFactory()
    }

    @Provides
    fun detailAdvertListData(): AdvertDataListFactory {
        return AdvertDataListFactory()
    }
}