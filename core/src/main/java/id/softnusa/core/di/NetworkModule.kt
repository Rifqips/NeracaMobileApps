package id.softnusa.core.di

import android.content.Context
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Module
import dagger.Provides
import id.softnusa.core.data.local.datastore.ApplicationDataStore
import id.softnusa.core.data.remote.model.BaseResponseDto
import id.softnusa.core.data.remote.model.request.auth.RequestTokentDto
import id.softnusa.core.data.remote.model.response.auth.ResponseLoginDto
import id.softnusa.core.di.qualifier.BaseUrl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import kotlinx.coroutines.flow.first
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
        tokenDataStore: ApplicationDataStore,
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

                    loadTokens {

                        val token = tokenDataStore.getToken().first()

                        token?.let {
                            BearerTokens(
                                accessToken = it,
                                refreshToken = ""
                            )
                        }
                    }

                    refreshTokens {

                        val oldRefreshToken = oldTokens?.refreshToken
                            ?: return@refreshTokens null

                        try {

                            val response: BaseResponseDto<ResponseLoginDto> =
                                client.post("auth/refresh") {
                                    setBody(
                                        RequestTokentDto(
                                            refreshToken = oldRefreshToken
                                        )
                                    )
                                }.body()

                            val newAccess = response.data?.accessToken
                                ?: return@refreshTokens null

                            val newRefresh = response.data.refreshToken

                            tokenDataStore.saveToken(newAccess)

                            BearerTokens(
                                accessToken = newAccess,
                                refreshToken = newRefresh
                            )

                        } catch (e: Exception) {
                            null
                        }
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