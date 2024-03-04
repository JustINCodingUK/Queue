package io.github.justincodinguk.core.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class User(
    @JsonNames("account_id") val accountId: Int = 0,
    @JsonNames("user_id") val userId: Int = 0,
    @JsonNames("user_type") val userType: UserType = UserType.REGISTERED,
    @JsonNames("display_name") val displayName: String,
    @JsonNames("accept_rate") val acceptRate: Float = 0f,
    @JsonNames("profile_image") val profileImage: String = "",
    val link: String = "",
    val reputation: Int = 1,
    val badges: BadgeCollection = BadgeCollection()
) {

    companion object {
        fun emptyUser(): User {
            return User(displayName = "")
        }
    }

}
