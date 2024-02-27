package io.github.justincodinguk.core.model

import io.github.justincodinguk.core.model.serializers.DateSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import java.util.Date

@Serializable
data class Comment @OptIn(ExperimentalSerializationApi::class) constructor(
    val user: User,
    val edited: Boolean,
    val score: Int,
    val body: String,

    @Serializable(DateSerializer::class)
    @JsonNames("creation_date")
    val creationDate: Date,

    @JsonNames("post_id") val postId: Int,
    @JsonNames("comment_id") val commentId: Int,
    @JsonNames("content_license") val contentLicense: String
)