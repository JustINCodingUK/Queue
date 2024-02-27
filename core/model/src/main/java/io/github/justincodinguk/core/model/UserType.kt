package io.github.justincodinguk.core.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
@OptIn(ExperimentalSerializationApi::class)
enum class UserType {
    @JsonNames("unregistered") UNREGISTERED,
    @JsonNames("registered") REGISTERED,
    @JsonNames("moderator") MODERATOR,
    @JsonNames("team_admin") TEAM_ADMIN,
    @JsonNames("does_not_exist") DOES_NOT_EXIST
}