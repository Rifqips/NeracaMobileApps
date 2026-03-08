package id.softnusa.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.softnusa.core.data.remote.api.AuthApi
import id.softnusa.core.data.remote.api.TransactionApi
import id.softnusa.core.data.remote.service.AuthApiImpl
import id.softnusa.core.data.remote.service.TransactionApiImpl
import id.softnusa.core.data.repository.AuthRepositoryImpl
import id.softnusa.core.data.repository.TransactionRepositoryImpl
import id.softnusa.core.domain.repository.AuthRepository
import id.softnusa.core.domain.repository.TransactionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindAuthApi(
        impl: AuthApiImpl
    ): AuthApi

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(
        impl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    abstract fun bindTransactionApi(
        impl: TransactionApiImpl
    ): TransactionApi
}