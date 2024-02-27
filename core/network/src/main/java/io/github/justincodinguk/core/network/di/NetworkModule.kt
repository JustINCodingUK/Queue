package io.github.justincodinguk.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import io.github.justincodinguk.core.network.fake.FakeNetworkDataSource
import io.github.justincodinguk.core.network.retrofit.StackExchangeNetworkDataSource

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    @Binds
    fun bindStackExchangeNetworkDataSource(
        impl: StackExchangeNetworkDataSource
    ) : QueueNetworkDataSource

}