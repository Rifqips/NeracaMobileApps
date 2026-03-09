package id.softnusa.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.softnusa.core.data.local.token.TokenManager
import id.softnusa.core.data.remote.interceptor.AuthInterceptor
import id.softnusa.core.data.remote.interceptor.RefreshInterceptor
import id.softnusa.core.data.remote.interceptor.TokenAuthenticator
import id.softnusa.core.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        tokenManager: TokenManager
    ): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }

    @Provides
    @Singleton
    fun provideRefreshInterceptor(
        tokenManager: TokenManager,
        authRepository: AuthRepository
    ): RefreshInterceptor {
        return RefreshInterceptor(tokenManager, authRepository)
    }

    @Provides
    @Singleton
    fun provideTokenAuthenticator(
        tokenManager: TokenManager,
        authRepository: AuthRepository
    ): TokenAuthenticator {
        return TokenAuthenticator(tokenManager, authRepository)
    }
}