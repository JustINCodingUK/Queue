package io.github.justincodinguk.core.network.fake

import android.util.Log
import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.model.User
import io.github.justincodinguk.core.model.UserType
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import kotlinx.coroutines.delay
import java.util.Date
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor() : QueueNetworkDataSource {

    private val user = User(
        31902141,
        21414214,
        UserType.REGISTERED,
        "JustinW",
        100.0f,
        "https://example.com",
        "https://example.com",
        2756
    )

    val question = Question(
        user,
        listOf("c++","java"),
        12,
        "https://example.com",
        "What is difference between c++ and java?",
        "Can someone help?",
        false,
        12,
        1,
        5221415,
        0,
        "MIT",
        Date(1709134222000L),
        Date(1709134222000L),
    )

    override suspend fun getQuestionsByActivity(pageNum: Int): List<Question> {
        Log.d("FAKE_NETWORK_RESOURCE", "Getting list")
        delay(1000)
        return List(10) { question }
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

    override suspend fun searchQuestion(searchPhrase: String, tags: List<String>): List<Question> {
        return listOf()
    }

}