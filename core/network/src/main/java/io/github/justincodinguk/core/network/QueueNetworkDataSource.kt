package io.github.justincodinguk.core.network

import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question

interface QueueNetworkDataSource {

    suspend fun getQuestionsByActivity(pageNum: Int) : List<Question>

    suspend fun getQuestionById(id: Int) : Question

    suspend fun getAnswersForQuestionById(id: Int): List<Answer>

    suspend fun getCommentsOnQuestionById(id: Int): List<Comment>

    suspend fun searchQuestion(searchPhrase: String, tags: List<String>): List<Question>

}