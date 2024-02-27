package io.github.justincodinguk.core.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class User @OptIn(ExperimentalSerializationApi::class) constructor(
    @JsonNames("account_id") val accountId: Int,
    @JsonNames("user_id") val userId: Int,
    @JsonNames("user_type") val userType: UserType,
    @JsonNames("display_name") val displayName: String,
    @JsonNames("accept_rate") val acceptRate: Float,
    @JsonNames("profile_image") val profileImage: String,
    val link: String,
    val reputation: Int
)
