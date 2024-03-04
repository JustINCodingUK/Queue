package io.github.justincodinguk.core.model

import io.github.justincodinguk.core.model.serializers.DateSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import java.util.Date

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class Question(
    val owner: User,
    val tags: List<String>,
    val score: Int,
    val link: String = "",
    val title: String,
    val body: String = "",

    @JsonNames("is_answered") val isAnswered: Boolean,
    @JsonNames("view_count") val viewCount: Int,
    @JsonNames("answer_count") val answerCount: Int,
    @JsonNames("question_id") val questionId: Int = 0,
    @JsonNames("accepted_answer_id") val acceptedAnswerId: Int = 0,
    @JsonNames("content_license") val contentLicense: String = "",

    @Serializable(DateSerializer::class)
    @JsonNames("last_activity_date")
    val lastActivityDate: Date,

    @Serializable(DateSerializer::class)
    @JsonNames("closed_date")
    val closedDate: Date = Date(0),

    @Serializable(DateSerializer::class)
    @JsonNames("creation_date")
    val creationDate: Date = Date(0),

    @Serializable(DateSerializer::class)
    @JsonNames("last_edit_date")
    val lastEditDate: Date? = null,

    @JsonNames("has_more")
    var hasMore: Boolean = true
) {
    companion object {
        fun emptyQuestion(): Question {
            return Question(
                owner = User.emptyUser(),
                tags = listOf(),
                score = 0,
                title = "",
                isAnswered = false,
                viewCount = -1,
                answerCount = -1,
                lastActivityDate = Date(0),
            )
        }
    }
}