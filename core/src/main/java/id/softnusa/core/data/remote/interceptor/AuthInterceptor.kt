package id.softnusa.core.data.remote.interceptor

import id.softnusa.core.data.local.token.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking {
            tokenManager.getAccessToken()
        }

        val request = chain.request()
            .newBuilder()
            .apply {
                token?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }
            .build()

        return chain.proceed(request)
    }
}