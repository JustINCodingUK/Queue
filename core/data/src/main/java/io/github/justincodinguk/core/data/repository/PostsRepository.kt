package io.github.justincodinguk.core.data.repository

import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val dataSource: QueueNetworkDataSource
) {

    fun getQuestionsByActivity() = flow<List<Question>> {

    }

}