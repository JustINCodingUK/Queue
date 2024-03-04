package io.github.justincodinguk.features.posts

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.justincodinguk.core.model.SearchQuery
import io.github.justincodinguk.core.ui.posts.QuestionCard
import io.github.justincodinguk.core.ui.app.QueueAppBar
import io.github.justincodinguk.core.ui.posts.QueueSearchBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PostsRoute(
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel = hiltViewModel(),
    loadQuestionDetails: (Int) -> Unit
) {

    val posts = viewModel.posts.collectAsLazyPagingItems()
    val postsState by viewModel.postsState.collectAsState()

    var searchValue by remember {
        mutableStateOf("")
    }
    var isSearching by remember {
        mutableStateOf(false)
    }

    val nestedScroll = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier,
        topBar = { QueueAppBar(scrollBehavior = nestedScroll) }
    ) { paddingValues ->
        if (isSearching and (posts.itemCount != 0)) {
            isSearching = false
        }

        Column(
            modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            QueueSearchBar(
                searchTextValue = searchValue,
                onSearch = {
                    val searchQuery = SearchQuery(it, listOf())
                    isSearching = true
                    viewModel.searchQuestions(searchQuery)
                },
                onActiveChange = { viewModel.activateSearchBar(it) },
                isActive = postsState.mode == PostsMode.SEARCH,
                searchResults = posts,
                onSearchTextValueChange = { searchValue = it },
                onFilterButtonClick = {},
                loading = isSearching,
                onResultItemClick = loadQuestionDetails,
                modifier = Modifier.padding(
                    horizontal = if (postsState.mode == PostsMode.SEARCH) 0.dp else 16.dp
                )
            )

            Text(
                text = "Top Questions",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Start)
            )
            Log.d("POSTS_STATUS", (postsState.status).toString())
            if (posts.itemCount == 0) {
                CircularProgressIndicator(
                    Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    Modifier
                        .nestedScroll(nestedScroll.nestedScrollConnection)
                ) {
                    items(posts.itemCount) { index ->
                        QuestionCard(
                            question = posts[index]!!,
                            onBookmarkClicked = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    loadQuestionDetails(posts[index]!!.questionId)
                                }
                        )
                    }
                }
            }
        }
    }
}