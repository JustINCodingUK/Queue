package io.github.justincodinguk.core.network.retrofit

import kotlinx.serialization.Serializable

@Serializable
data class StackExchangeNetworkResponse<T>(
    val items: T
)