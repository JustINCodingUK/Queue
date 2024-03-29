package io.github.justincodinguk.core.model

import io.github.justincodinguk.core.model.serializers.DateSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import java.util.Date

@Serializable
data class Answer @OptIn(ExperimentalSerializationApi::class) constructor(
    val owner: User = User.emptyUser(),
    val score: Int = 0,
    val body: String = "",

    @JsonNames("is_accepted") val isAccepted: Boolean = false,
    @JsonNames("answer_id") val answerId: Int = -1,
    @JsonNames("question_id") val questionId: Int = -1,
    @JsonNames("content_license") val contentLicense: String = "",

    @Serializable(DateSerializer::class)
    @JsonNames("last_activity_date")
    val lastActivityDate: Date,

    @Serializable(DateSerializer::class)
    @JsonNames("creation_date")
    val creationDate: Date,

    @Serializable(DateSerializer::class)
    @JsonNames("last_edit_date")
    val lastEditDate: Date? = null
)