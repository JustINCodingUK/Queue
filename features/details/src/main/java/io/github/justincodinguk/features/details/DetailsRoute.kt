package io.github.justincodinguk.features.details

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.ui.app.QueueAppBar
import io.github.justincodinguk.core.ui.details.DetailsQuestionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsRoute(
    questionId: Int,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    viewModel.loadDetailsFor(questionId)
    val detailsState by viewModel.detailsState.collectAsState()

    val scrollMode = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = { QueueAppBar(
            scrollBehavior = scrollMode
        ) }
    ) { paddingValues ->

        Column(
            Modifier
                .nestedScroll(scrollMode.nestedScrollConnection)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(detailsState.question == Question.emptyQuestion()) {
                CircularProgressIndicator()
            } else {
                LazyColumn(Modifier.padding(paddingValues)) {
                    item {
                        DetailsQuestionCard(question = detailsState.question)
                    }
                }
            }
        }

    }

}