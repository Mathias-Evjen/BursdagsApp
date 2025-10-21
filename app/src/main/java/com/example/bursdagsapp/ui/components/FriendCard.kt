package com.example.bursdagsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.theme.BursdagsAppTheme

@Composable
fun FriendCard(
    modifier: Modifier = Modifier,
    friend: Friend,
    padding: Int,

    onFriendClick: ((Friend) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(bottom = 4.dp, start = 4.dp, end = 4.dp)
            .clickable { onFriendClick?.invoke(friend) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = friend.name, style = MaterialTheme.typography.titleLarge)
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${friend.birthDay}/${friend.birthMonth}", style = MaterialTheme.typography.titleLarge)
            }

            Box(modifier = Modifier.weight(0.5f))
        }
    }
}
