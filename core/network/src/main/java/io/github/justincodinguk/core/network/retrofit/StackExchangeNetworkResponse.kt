package io.github.justincodinguk.core.network.retrofit

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class StackExchangeNetworkResponse<T>(
    val items: T,
    @JsonNames("has_more") val hasMore: Boolean = true
)