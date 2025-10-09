package com.example.bursdagsapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.theme.BursdagsAppTheme

@Composable
fun VennCard(
    modifier: Modifier = Modifier,
    friend: Friend
) {
    Card(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = friend.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "" + friend.birthDay + "/" + friend.birthMonth, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Preview
@Composable
fun VennCardPreview() {
    val friend = Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14)

    BursdagsAppTheme {
        VennCard(friend = friend)
    }
}