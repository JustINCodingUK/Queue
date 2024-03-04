package io.github.justincodinguk.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import io.github.justincodinguk.core.network.retrofit.StackExchangeNetworkDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkDataSourceModule {

    @Binds
    @Singleton
    fun bindStackExchangeNetworkDataSource(
        impl: StackExchangeNetworkDataSource
    ) : QueueNetworkDataSource

}