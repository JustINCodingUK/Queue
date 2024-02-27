package io.github.justincodinguk.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Inject

internal class StackExchangeNetworkDataSource @Inject constructor()
    : QueueNetworkDataSource {

    private val stackExchangePostsApiService = Retrofit.Builder()
        .baseUrl("https://api.stackexchange.com/2.3/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()
        .create(StackExchangePostsApiService::class.java)

    override suspend fun getQuestionsByActivity(pageNum: Int)
        = stackExchangePostsApiService.getQuestions(pageNum).items

    override suspend fun getQuestionById(id: Int)
        = stackExchangePostsApiService.getQuestionById(id).items.single()

    override suspend fun getAnswersForQuestionById(id: Int): List<Answer>
        = stackExchangePostsApiService.getAnswersForQuestionById(id).items

    override suspend fun getCommentsOnQuestionById(id: Int): List<Comment>
        = stackExchangePostsApiService.getCommentsOnQuestionById(id).items

}