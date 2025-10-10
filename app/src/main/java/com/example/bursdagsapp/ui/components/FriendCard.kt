package com.example.bursdagsapp.ui.components

import android.media.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bursdagsapp.R
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.theme.BursdagsAppTheme

@Composable
fun FriendCard(
    modifier: Modifier = Modifier,
    friend: Friend? = null,
    personIcon: ImageVector? = null,
    personIconDesc: String? = null,
    cakeIcon: Painter? = null,
    cakeIconDesc: String? = null,

    padding: Int
) {
    Card(
        modifier = Modifier.padding(bottom = 4.dp, start = 4.dp, end = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (friend != null) {
                    Text(text = friend.name, style = MaterialTheme.typography.titleLarge)
                }
                if (personIcon != null) {
                    Icon(imageVector = personIcon, contentDescription = personIconDesc)
                }
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (friend != null) {
                    Text(text = "${friend.birthDay}/${friend.birthMonth}", style = MaterialTheme.typography.titleLarge)
                }
                if (cakeIcon != null) {
                    Icon(painter = cakeIcon, contentDescription = cakeIconDesc)
                }
            }

            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (friend != null) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "View options")
                }
            }
        }
    }
}

@Preview
@Composable
fun VennFriendPreview() {
    val friend = Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14)

    BursdagsAppTheme {
        FriendCard(friend = friend, padding = 16)
    }
}