package id.softnusa.core.data.remote.interceptor

import id.softnusa.core.data.local.token.TokenManager
import id.softnusa.core.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RefreshInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        val token = runBlocking {
            tokenManager.getAccessToken()
        }

        request = request.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        var response = chain.proceed(request)

        if (response.code == 403 || response.code == 401) {

            val refreshToken = runBlocking {
                tokenManager.getRefreshToken()
            } ?: return response

            val newToken = runBlocking {
                authRepository.refreshToken(refreshToken)
            }

            val newAccess = newToken.data?.accessToken ?: return response

            runBlocking {
                tokenManager.saveToken(
                    newAccess,
                    newToken.data.refreshToken
                )
            }

            val newRequest = request.newBuilder()
                .header("Authorization", "Bearer $newAccess")
                .header("X-Retry", "true")
                .build()

            response.close()

            response = chain.proceed(newRequest)
        }

        return response
    }
}