package io.github.justincodinguk.core.data.pager_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.network.QueueNetworkDataSource
import javax.inject.Inject

internal class PostsPagingSource @Inject constructor(
    private val isSearchMode: Boolean,
    private val postsSource: QueueNetworkDataSource,
    private val tags: List<String> = listOf(),
    private val searchPhrase: String = "",
) : PagingSource<Int, Question>() {

    override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {

        return try {

            val nextPage = params.key ?: 1

            val response = if(isSearchMode) {
                postsSource.searchQuestion(searchPhrase, tags)
            } else {
                postsSource.getQuestionsByActivity(nextPage+1)
            }

            LoadResult.Page(
                data = response,
                nextKey = if(response.last().hasMore) nextPage+1 else null,
                prevKey = if(nextPage == 1) null else nextPage-1
            )

        } catch (e: Exception) {
            LoadResult.Invalid()
        }
    }
}