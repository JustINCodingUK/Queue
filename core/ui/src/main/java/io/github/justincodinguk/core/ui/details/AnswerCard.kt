package io.github.justincodinguk.core.ui.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.justincodinguk.core.model.Answer
import io.github.justincodinguk.core.model.BadgeCollection
import io.github.justincodinguk.core.model.User
import io.github.justincodinguk.core.model.UserType
import io.github.justincodinguk.core.ui.IconText
import io.github.justincodinguk.core.ui.R
import io.github.justincodinguk.core.ui.theme.QueueApplicationTheme
import java.util.Date

@Composable
fun AnswerCard(
    answer: Answer,
    onUpvote: () -> Unit,
    onDownvote: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Green)
    ) {
        ElevatedCard(
            Modifier
                .padding(if (answer.isAccepted) 2.dp else 0.dp)
        ) {
            if (answer.isAccepted) {
                IconText(
                    iconPainter = painterResource(id = R.drawable.ic_check),
                    text = "Accepted Answer",
                    contentDescription = "Solved",
                    iconTint = Color.Green,
                    textColor = Color.Green,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            } else {
                Spacer(Modifier.height(16.dp))
            }

            PostInfo(
                views = -1,
                lastActivityDate = answer.lastActivityDate,
                creationDate = answer.creationDate,
                lastEditDate = answer.lastEditDate,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )

            Text(
                text = answer.body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                VoteChip(
                    score = answer.score,
                    onUpvote = onUpvote,
                    onDownVote = onDownvote,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .heightIn(max = 32.dp)
                )

                UserInfo(
                    user = answer.owner,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .heightIn(max = 72.dp)
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun AnswerPreview() {

    val user = User(
        31902141,
        21414214,
        UserType.REGISTERED,
        "JustinW",
        100.0f,
        "https://example.com",
        "https://example.com",
        2756,
        badges = BadgeCollection(43, 20, 4)
    )

    val answer = Answer(
        owner = user,
        score = 5,
        body = "This is an example answer nothing so serious here tbh lol. Just read it for the sake of reading it nothing else here too special. Why're you still reading this?",
        isAccepted = false,
        creationDate = Date(0),
        lastActivityDate = Date(0)
    )

    QueueApplicationTheme(isDebug = true) {

        AnswerCard(
            answer = answer,
            onUpvote = { /*TODO*/ },
            onDownvote = { /*TODO*/ }
        )

    }
}