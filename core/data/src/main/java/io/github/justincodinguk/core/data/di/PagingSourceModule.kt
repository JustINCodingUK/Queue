package io.github.justincodinguk.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.justincodinguk.core.data.pager_source.PostsPagingSource
import io.github.justincodinguk.core.network.QueueNetworkDataSource

@Module
@InstallIn(SingletonComponent::class)
internal object PagingSourceModule {

    @Provides
    fun providePagingSource(
        queueNetworkDataSource: QueueNetworkDataSource
    ) = PostsPagingSource(false, queueNetworkDataSource)

}