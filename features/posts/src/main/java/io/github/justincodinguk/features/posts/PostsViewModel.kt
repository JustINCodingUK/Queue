package io.github.justincodinguk.features.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.justincodinguk.core.data.repository.PostsRepository
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.model.SearchQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val _postsState = MutableStateFlow(
        PostsRouteState(PostsMode.ALL, PostsStatus.LOADING)
    )
    val postsState = _postsState.asStateFlow()


    private val currentMode = MutableStateFlow(PostsMode.ALL)
    private val currentStatus = MutableStateFlow(PostsStatus.LOADING)
    private val searchResults = MutableStateFlow(PagingData.empty<Question>())
    private val questions = postsRepository.getPostsByActivity()
        .cachedIn(viewModelScope)

    val posts = combine(currentMode, searchResults, questions) { mode, searchResults, questions ->
        if (mode == PostsMode.ALL) {
            questions
        } else {
            searchResults
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PagingData.empty())

    init {
        Log.d("ViewModel", _postsState.value.status.toString())
        viewModelScope.launch {
            questions
                .onEach {
                    if(currentStatus.value == PostsStatus.LOADING) {
                        _postsState.emit(PostsRouteState(PostsMode.ALL, PostsStatus.DONE))
                    }
                }
                .collect {}
        }
    }


    fun searchQuestions(searchQuery: SearchQuery) {
        searchResults.value = PagingData.empty()
        viewModelScope.launch {
            _postsState.emit(PostsRouteState(PostsMode.SEARCH, PostsStatus.LOADING))
            val results = postsRepository.searchQuestion(searchQuery.inTitle, searchQuery.tags)
                .cachedIn(viewModelScope)

            results.collect {
                searchResults.emit(it)
                _postsState.emit(PostsRouteState(PostsMode.SEARCH, PostsStatus.DONE))
            }
        }
    }

    fun activateSearchBar(isActive: Boolean) {
        viewModelScope.launch {
            if(isActive) {
                currentMode.emit(PostsMode.SEARCH)
                _postsState.emit(
                    PostsRouteState(PostsMode.SEARCH, PostsStatus.INITIAL)
                )
            } else {
                currentMode.emit(PostsMode.ALL)
                _postsState.emit(
                    PostsRouteState(PostsMode.ALL, PostsStatus.DONE)
                )
            }
        }
    }
}