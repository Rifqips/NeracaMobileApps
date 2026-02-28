package id.softnusa.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.softnusa.core.data.local.datastore.ApplicationDataStore
import id.softnusa.core.data.local.datastore.ApplicationDataStoreImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreBindingModule {

    @Binds
    @Singleton
    abstract fun bindApplicationDataStore(
        impl: ApplicationDataStoreImpl
    ): ApplicationDataStore
}