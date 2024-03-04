package io.github.justincodinguk.features.details

import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question

data class DetailsState(
    val question: Question,
    val answers: List<Answer>,
    val comments: List<Comment>
) {
    companion object {
        fun initialState() : DetailsState {
            return DetailsState(Question.emptyQuestion(), listOf(), listOf())
        }
    }
}