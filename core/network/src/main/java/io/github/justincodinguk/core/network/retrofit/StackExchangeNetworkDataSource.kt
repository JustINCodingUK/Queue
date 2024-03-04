package io.github.justincodinguk.core.network.retrofit

import io.github.justincodinguk.core.ext.toPlainText
import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import javax.inject.Inject

internal class StackExchangeNetworkDataSource @Inject constructor(
    private val stackExchangePostsApiService: StackExchangePostsApiService
) : QueueNetworkDataSource {
    override suspend fun getQuestionsByActivity(pageNum: Int)
        = stackExchangePostsApiService.getQuestions(pageNum).items.map {
            it.copy(title = it.title.toPlainText())
    }

    override suspend fun getQuestionById(id: Int): Question {
        val question = stackExchangePostsApiService.getQuestionById(id).items.single()
        return question.copy(
            title = question.title.toPlainText(),
            body = question.body.toPlainText()
        )
    }

    override suspend fun getAnswersForQuestionById(id: Int): List<Answer>
        = stackExchangePostsApiService.getAnswersForQuestionById(id).items.map {
            it.copy(body = it.body.toPlainText())
    }

    override suspend fun getCommentsOnQuestionById(id: Int): List<Comment>
        = stackExchangePostsApiService.getCommentsOnQuestionById(id).items.map {
            it.copy(body = it.body.toPlainText())
    }

    override suspend fun searchQuestion(searchPhrase: String, tags: List<String>): List<Question> {
        val tagsJoined = tags.joinToString(";")
        val response =  stackExchangePostsApiService.searchQuestion(searchPhrase, tagsJoined)
        response.items.last().hasMore = response.hasMore
        return response.items.map {
            it.copy(title = it.title.toPlainText())
        }
    }
}