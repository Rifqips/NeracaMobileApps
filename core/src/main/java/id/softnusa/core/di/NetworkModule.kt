package id.softnusa.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.softnusa.core.data.local.datastore.ApplicationDataStore
import id.softnusa.core.data.remote.api.AuthApi
import id.softnusa.core.di.qualifier.BaseUrl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
        tokenDataStore: ApplicationDataStore,
        api: AuthApi,
        @BaseUrl baseUrl: String
    ): HttpClient {

        return HttpClient(OkHttp) {

            engine {
                preconfigured = OkHttpClient.Builder()
                    .addInterceptor(
                        ChuckerInterceptor.Builder(context)
                            .alwaysReadResponseBody(true)
                            .build()
                    )
                    .build()
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(Auth) {
                bearer {

                    sendWithoutRequest { true }

                    loadTokens {
                        val access = tokenDataStore.getToken().first()
                        val refresh = tokenDataStore.getRefreshToken().first()

                        if (access != null && refresh != null) {
                            BearerTokens(access, refresh)
                        } else null
                    }

                    refreshTokens {

                        val refreshToken = oldTokens?.refreshToken ?: return@refreshTokens null

                        val response = api.refreshToken(refreshToken)

                        val newAccess = response.data?.accessToken ?: return@refreshTokens null
                        val newRefresh = response.data.refreshToken

                        tokenDataStore.saveTokens(newAccess, newRefresh)

                        BearerTokens(newAccess, newRefresh)
                    }
                }
            }

            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}