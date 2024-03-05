package io.github.justincodinguk.core.ui.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.justincodinguk.core.model.BadgeCollection
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.model.User
import io.github.justincodinguk.core.model.UserType
import io.github.justincodinguk.core.ui.IconText
import io.github.justincodinguk.core.ui.R
import io.github.justincodinguk.core.ui.posts.TagElement
import io.github.justincodinguk.core.ui.theme.QueueApplicationTheme
import java.util.Date

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsQuestionCard(
    question: Question,
    modifier: Modifier = Modifier
) {

    ElevatedCard(
        modifier.padding(16.dp)
    ) {
        if(question.isAnswered) {
            IconText(
                iconPainter = painterResource(id = R.drawable.ic_check),
                text = "SOLVED",
                contentDescription = "Solved",
                iconTint = Color.Green,
                textColor = Color.Green,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(48.dp))
        }

        Text(
            text = question.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        PostInfo(
            views = question.viewCount,
            lastActivityDate = question.lastActivityDate,
            creationDate = question.creationDate,
            lastEditDate = question.lastEditDate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        HorizontalDivider(
            Modifier
                .height(10.dp)
                .padding(horizontal = 8.dp)
        )

        Text(
            text = question.body,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )

        FlowRow(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)) {
            question.tags.forEach {
                TagElement(tagName = it)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            VoteChip(
                score = question.score,
                onUpvote = { },
                onDownVote = { }
            )

            UserInfo(user = question.owner)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun DetailsQuestionCardPreview() {

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

    QueueApplicationTheme(isDebug = true) {
        DetailsQuestionCard(question = Question(
            owner = user,
            tags = listOf("java","kotlin"),
            score = 12,
            title = "This is just an example question",
            body = "This is an example answer nothing so serious here tbh lol. Just read it for the sake of reading it nothing else here too special.",
            isAnswered = true,
            viewCount = 1221,
            answerCount = 4,
            lastActivityDate = Date(0)
        ))
    }

}