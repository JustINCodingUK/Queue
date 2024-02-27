package io.github.justincodinguk.core.model

import io.github.justincodinguk.core.model.serializers.DateSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import java.util.Date

@Serializable
data class Question @OptIn(ExperimentalSerializationApi::class) constructor(
    val owner: User,
    val tags: List<String>,
    val score: Int,
    val link: String,
    val title: String,
    val body: String = "",

    @JsonNames("is_answered") val isAnswered: Boolean,
    @JsonNames("view_count") val viewCount: Int,
    @JsonNames("answer_count") val answerCount: Int,
    @JsonNames("question_id") val questionId: Int,
    @JsonNames("content_license") val contentLicense: String,

    @Serializable(DateSerializer::class)
    @JsonNames("last_activity_date")
    val lastActivityDate: Date,

    @Serializable(DateSerializer::class)
    @JsonNames("creation_date")
    val creationDate: Date,

    @Serializable(DateSerializer::class)
    @JsonNames("last_edit_date")
    val lastEditDate: Date?
)