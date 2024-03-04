package io.github.justincodinguk.core.data.repository

import androidx.paging.PagingData
import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PostsRepository {

    fun getPostsByActivity(): Flow<PagingData<Question>>

    fun getQuestionById(id: Int): Flow<Question>

    fun getAnswersForQuestionById(id: Int): Flow<List<Answer>>

    fun getCommentsForQuestionById(id: Int): Flow<List<Comment>>

    fun searchQuestion(searchPhrase: String, tags: List<String>): Flow<PagingData<Question>>

}