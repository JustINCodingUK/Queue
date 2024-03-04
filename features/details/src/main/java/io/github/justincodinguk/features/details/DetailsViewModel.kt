package io.github.justincodinguk.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.justincodinguk.core.data.repository.PostsRepository
import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.Comment
import io.github.justincodinguk.core.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val question = MutableStateFlow(Question.emptyQuestion())

    private val answers = MutableStateFlow(listOf<Answer>())

    private val comments = MutableStateFlow(listOf<Comment>())

    val detailsState = combine(
        question,
        answers,
        comments
    ) { question, answers, comments ->
        DetailsState(question, answers, comments)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        DetailsState.initialState()
    )

    fun loadDetailsFor(questionId: Int) {
        viewModelScope.launch {
            with(postsRepository) {
                getQuestionById(questionId).collect { question.emit(it) }
                getAnswersForQuestionById(questionId).collect { answers.emit(it) }
                getCommentsForQuestionById(questionId).collect { comments.emit(it) }
            }
        }
    }

}