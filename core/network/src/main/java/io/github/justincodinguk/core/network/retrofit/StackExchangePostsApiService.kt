package io.github.justincodinguk.core.network.retrofit

import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question
import retrofit2.http.GET
import retrofit2.http.Path

internal interface StackExchangePostsApiService {

    @GET("questions?page={page}&pagesize=10&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions(@Path("page") pageNum: Int): StackExchangeNetworkResponse<List<Question>>

    @GET("questions/{id}?order=desc&sort=activity&site=stackoverflow&filter=with-body")
    suspend fun getQuestionById(@Path("id") id: Int): StackExchangeNetworkResponse<List<Question>>

    @GET("questions/{id}/answers?order=desc&sort=votes&site=stackoverflow&filter=withbody")
    suspend fun getAnswersForQuestionById(@Path("id") id: Int): StackExchangeNetworkResponse<List<Answer>>

    @GET("questions/{id}/comments?order=desc&sort=votes&site=stackoverflow&filter=withbody")
    suspend fun getCommentsOnQuestionById(@Path("id") id: Int): StackExchangeNetworkResponse<List<Comment>>

}