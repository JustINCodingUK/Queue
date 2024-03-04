package io.github.justincodinguk.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.justincodinguk.core.data.repository.NetworkPostsRepository
import io.github.justincodinguk.core.data.repository.PostsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindNetworkPostsRepository(
        impl: NetworkPostsRepository
    ) : PostsRepository

}