package io.github.justincodinguk.core.network.fake

import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.network.QueueNetworkDataSource

class FakeNetworkDataSource : QueueNetworkDataSource {

    override suspend fun getQuestionsByActivity(pageNum: Int): List<Question> {
        return listOf()
    }

    override suspend fun getQuestionById(id: Int): Question {
        TODO()
    }

    override suspend fun getAnswersForQuestionById(id: Int): List<Answer> {
        return listOf()
    }

    override suspend fun getCommentsOnQuestionById(id: Int): List<Comment> {
        return listOf()
    }

}