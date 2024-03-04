package io.github.justincodinguk.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.github.justincodinguk.core.data.pager_source.PostsPagingSource
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class NetworkPostsRepository @Inject constructor(
    private val queueNetworkDataSource: QueueNetworkDataSource,
) : PostsRepository {

    override fun getPostsByActivity() = Pager(
        PagingConfig(50)
    ) {
        PostsPagingSource(false, queueNetworkDataSource)
    }.flow

    override fun getQuestionById(id: Int) = flow {
        val question = queueNetworkDataSource.getQuestionById(id)
        emit(question)
    }

    override fun getAnswersForQuestionById(id: Int) = flow {
        val answers = queueNetworkDataSource.getAnswersForQuestionById(id)
        emit(answers)
    }

    override fun getCommentsForQuestionById(id: Int) = flow {
        val comments = queueNetworkDataSource.getCommentsOnQuestionById(id)
        emit(comments)
    }

    override fun searchQuestion(searchPhrase: String, tags: List<String>) = Pager(
        PagingConfig(50)
    ) {
        PostsPagingSource(true, queueNetworkDataSource, tags, searchPhrase)
    }.flow

}