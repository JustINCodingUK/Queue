package io.github.justincodinguk.soqueue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import io.github.justincodinguk.soqueue.navigation.QueueNavGraph
import io.github.justincodinguk.core.ui.theme.QueueApplicationTheme

import android.content.Context

@AndroidEntryPoint
class QueueActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            QueueApplicationTheme {
                QueueNavGraph()
            }
        }
    }
}