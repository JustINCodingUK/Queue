package io.github.justincodinguk.core.ui.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueSearchBar(
    modifier: Modifier = Modifier,
    searchTextValue: String,
    onSearchTextValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    isActive: Boolean = false,
    onActiveChange: (Boolean) -> Unit,
    onFilterButtonClick: () -> Unit,
    onResultItemClick: (Int) -> Unit,
    loading: Boolean = false,
    searchResults: LazyPagingItems<Question>
) {
    SearchBar(
        query = searchTextValue,
        onQueryChange = onSearchTextValueChange,
        onSearch = onSearch,
        active = isActive,
        onActiveChange = onActiveChange,
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null)},
        trailingIcon = { IconButton(onFilterButtonClick) { Icon(painterResource(id = R.drawable.ic_filter), null)} },
        modifier = modifier
    ) {
        if(!loading) {
            LazyColumn {
                items(searchResults.itemCount) {
                    QuestionCard(
                        question = searchResults[it]!!,
                        onBookmarkClicked = {},
                        modifier = Modifier.clickable {
                            onResultItemClick(searchResults[it]!!.questionId)
                        }
                    )
                }
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
    }
}