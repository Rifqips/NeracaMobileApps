package id.softnusa.core.data.remote.interceptor

import android.util.Log
import id.softnusa.core.data.local.token.TokenManager
import id.softnusa.core.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        Log.d("check-data", "refreshToken triggered")
        // prevent infinite retry
        if (responseCount(response) >= 2) return null

        val refreshToken = runBlocking {
            tokenManager.getRefreshToken()
        } ?: return null

        val newToken = runBlocking {
            authRepository.refreshToken(refreshToken)
        }

        val accessToken = newToken.data?.accessToken ?: return null
        val refresh = newToken.data.refreshToken
        Log.d("check-data", "refreshToken = $refreshToken")
        runBlocking {
            tokenManager.saveToken(accessToken, refresh)
        }

        return response.request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var prior = response.priorResponse
        while (prior != null) {
            result++
            prior = prior.priorResponse
        }
        return result
    }
}