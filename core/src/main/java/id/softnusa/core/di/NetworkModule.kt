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
import id.softnusa.core.data.remote.api.TransactionApi
import id.softnusa.core.data.remote.interceptor.AuthInterceptor
import id.softnusa.core.data.remote.interceptor.RefreshInterceptor
import id.softnusa.core.data.remote.interceptor.TokenAuthenticator
import id.softnusa.core.data.remote.service.AuthApiImpl
import id.softnusa.core.data.remote.service.TransactionApiImpl
import id.softnusa.core.di.qualifier.BaseUrl
import id.softnusa.core.di.qualifier.RefreshClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
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
        authInterceptor: AuthInterceptor,
        refreshInterceptor: RefreshInterceptor,
        authenticator: TokenAuthenticator,
        @BaseUrl baseUrl: String
    ): HttpClient {

        return HttpClient(OkHttp) {

            engine {
                preconfigured = OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(refreshInterceptor)
                    .authenticator(authenticator)
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

            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }


    @Provides
    @Singleton
    @RefreshClient
    fun provideRefreshHttpClient(
        @ApplicationContext context: Context,
        @BaseUrl baseUrl: String
    ): HttpClient {

        return HttpClient(OkHttp) {

            engine {
                preconfigured = OkHttpClient.Builder()
                    .addInterceptor(
                        ChuckerInterceptor.Builder(context).build()
                    )
                    .build()
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }

    @Provides
    @Singleton
    fun provideAuthApi(
        @RefreshClient client: HttpClient
    ): AuthApi {
        return AuthApiImpl(client)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(
        client: HttpClient,
        dataStore: ApplicationDataStore
    ): TransactionApi {
        return TransactionApiImpl(client, dataStore)
    }

}