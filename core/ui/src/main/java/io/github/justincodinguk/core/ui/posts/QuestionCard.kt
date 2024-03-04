package io.github.justincodinguk.core.ui.posts

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import coil.compose.AsyncImage
import io.github.justincodinguk.core.ext.timeDiffString
import io.github.justincodinguk.core.ext.truncatedToString
import io.github.justincodinguk.core.model.Question
import io.github.justincodinguk.core.model.User
import io.github.justincodinguk.core.model.UserType
import io.github.justincodinguk.core.ui.IconText
import io.github.justincodinguk.core.ui.R
import io.github.justincodinguk.core.ui.theme.QueueApplicationTheme
import java.util.Date

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuestionCard(
    question: Question,
    onBookmarkClicked: (Question) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.padding(8.dp)
    ) {


        Column {
            Text(
                text = question.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .heightIn(max = 24.dp)
                    .padding(start = 8.dp)
            ) {
                AsyncImage(
                    model = question.owner.profileImage,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp)
                )

                Text(
                    text = question.owner.displayName,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = question.creationDate.timeDiffString(),
                    style = MaterialTheme.typography.labelMedium
                )
            }

            FlowRow(
                Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                for (tag in question.tags) {
                    TagElement(tagName = tag)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Right,
                modifier = Modifier.align(Alignment.End)
            ) {
                IconText(
                    iconPainter = painterResource(R.drawable.ic_check),
                    text = question.answerCount.toString(),
                    iconTint = if (question.isAnswered) Color.Green else Color.Black,
                    contentDescription = null
                )

                IconText(
                    iconPainter = painterResource(R.drawable.ic_views),
                    text = question.viewCount.truncatedToString(),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = { onBookmarkClicked(question) }) {
                    Icon(
                        painterResource(id = R.drawable.ic_save),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun QuestionCardPreview() {

    val user = User(
        31902141,
        21414214,
        UserType.REGISTERED,
        "JustinW",
        100.0f,
        "https://example.com",
        "https://example.com",
        2756
    )

    QueueApplicationTheme(isDebug = true) {
        QuestionCard(
            question = Question(
                user,
                listOf("c++", "java"),
                12,
                "https://example.com",
                "What is difference between c++ and java?",
                "Can someone help?",
                false,
                12,
                1,
                5221415,
                0,
                "MIT",
                Date(1709134222000L),
                Date(1709134222000L),
            ),
            {}
        )
    }
}