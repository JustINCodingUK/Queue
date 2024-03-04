package io.github.justincodinguk.core.network.retrofit

import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface StackExchangePostsApiService {

    @GET("questions?pagesize=50&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions(@Query("page") pageNum: Int): StackExchangeNetworkResponse<List<Question>>

    @GET("questions/{id}?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    suspend fun getQuestionById(@Path("id") id: Int): StackExchangeNetworkResponse<List<Question>>

    @GET("questions/{id}/answers?order=desc&sort=votes&site=stackoverflow&filter=withbody")
    suspend fun getAnswersForQuestionById(@Path("id") id: Int): StackExchangeNetworkResponse<List<Answer>>

    @GET("questions/{id}/comments?order=desc&sort=votes&site=stackoverflow&filter=withbody")
    suspend fun getCommentsOnQuestionById(@Path("id") id: Int): StackExchangeNetworkResponse<List<Comment>>

    @GET("search/?order=desc&sort=activity&site=stackoverflow")
    suspend fun searchQuestion(@Query("intitle") searchTerm: String, @Query("tagged") tagged: String): StackExchangeNetworkResponse<List<Question>>

}