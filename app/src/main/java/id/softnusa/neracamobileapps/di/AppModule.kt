package id.softnusa.neracamobileapps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.softnusa.core.di.qualifier.BaseUrl
import id.softnusa.neracamobileapps.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppConfigModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}