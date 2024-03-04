package io.github.justincodinguk.core.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.justincodinguk.core.ext.toSimpleDateString
import io.github.justincodinguk.core.ui.IconText
import io.github.justincodinguk.core.ui.R
import java.util.Date

@Composable
fun QuestionInfo(
    modifier: Modifier = Modifier,
    views: Int,
    lastActivityDate: Date,
    creationDate: Date,
    lastEditDate: Date?,
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        IconText(
            iconPainter = painterResource(id = R.drawable.ic_views),
            text = views.toString(),
            contentDescription = "Views"
        )

        IconText(
            iconPainter = painterResource(id = R.drawable.ic_create),
            text = creationDate.toSimpleDateString(),
            contentDescription = "Creation Date"
        )

        IconText(
            iconPainter = painterResource(id = R.drawable.ic_activity),
            text = lastActivityDate.toSimpleDateString(),
            contentDescription = "Last Active"
        )

        if(lastEditDate != null) {
            IconText(
                iconPainter = painterResource(id = R.drawable.ic_edit),
                text = lastEditDate.toSimpleDateString(),
                contentDescription = "Last Edited"
            )
        }
    }
}