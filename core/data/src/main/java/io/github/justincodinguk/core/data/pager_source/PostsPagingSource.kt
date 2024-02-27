package io.github.justincodinguk.core.data.pager_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import javax.inject.Inject

class PostsPagingSource @Inject constructor(
    private val postsSource: QueueNetworkDataSource
) : PagingSource<Int, Question>() {

    override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {
        return try {
            val nextPage = params.key ?: 1
            val response = postsSource.getQuestionsByActivity(nextPage)
            LoadResult.Page(
                data = response,
                nextKey = nextPage+1,
                prevKey = nextPage-1
            )
        } catch (e: Exception) {
            LoadResult.Invalid()
        }
    }
}