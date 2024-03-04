package io.github.justincodinguk.core.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.justincodinguk.core.model.User
import io.github.justincodinguk.core.ui.IconText
import io.github.justincodinguk.core.ui.R

@Composable
fun UserInfo(
    user: User,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier,
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            /* Use in prod
            AsyncImage(
                model = user.profileImage, 
                contentDescription = "User Profile Image",
                modifier = Modifier.size(32.dp)
            )
            */
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = user.displayName,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    user.reputation.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
                Row(horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                    IconText(
                        iconPainter = painterResource(R.drawable.ic_badge),
                        text = user.badges.gold.toString(),
                        contentDescription = "",
                        iconTint = Color.Yellow,
                    )
                    IconText(
                        iconPainter = painterResource(R.drawable.ic_badge),
                        text = user.badges.silver.toString(),
                        contentDescription = "",
                        iconTint = Color.LightGray,
                    )
                    IconText(
                        iconPainter = painterResource(R.drawable.ic_badge),
                        text = user.badges.bronze.toString(),
                        contentDescription = "",
                        iconTint = Color(0x89FF6A1F),
                    )
                }
            }
        }
    }
}
