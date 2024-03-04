package io.github.justincodinguk.core.ui.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.justincodinguk.core.ui.R

@Composable
fun VoteChip(
    score: Int,
    onUpvote: () -> Unit,
    onDownVote: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onUpvote) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_upvote),
                    contentDescription = "Upvote",
                    modifier = Modifier.padding(4.dp)
                )
            }
            Text(
                text = score.toString(),
                style = MaterialTheme.typography.labelLarge
            )
            IconButton(onClick = onDownVote) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_downvote),
                    contentDescription = "downvote",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}