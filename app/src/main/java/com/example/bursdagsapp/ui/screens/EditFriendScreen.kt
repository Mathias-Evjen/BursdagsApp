package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFriendScreen(
    friend: Friend,
    viewModel: FriendViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf(friend.name) }
    var isNameEmpty by remember { mutableStateOf(false) }

    var phoneNumber by remember { mutableStateOf(friend.phoneNumber) }
    var isPhoneNumberEmpty by remember { mutableStateOf(false) }

    var birthdayMessage by remember { mutableStateOf(friend.message) }

    val initialDateMillis = remember(friend) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        calendar.set(Calendar.MONTH, friend.birthMonth - 1)
        calendar.set(Calendar.DAY_OF_MONTH, friend.birthDay)
        calendar.timeInMillis
    }

    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis
    )

    val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
        val calendar = Calendar.getInstance().apply { timeInMillis = millis}
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        Pair(day, month)
    }

    var isSelectedDateEmpty by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Edit friend", style = MaterialTheme.typography.displayLarge)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )

        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it},
            label = { Text("Phone number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Box{
            OutlinedTextField(
                value = datePickerState.selectedDateMillis?.let { millis ->
                    // Use the millisecond Long value directly here
                    SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date(millis))
                } ?: "Select a date",
                onValueChange = {},
                readOnly = true,
                label = { Text("Birthday") },
                trailingIcon = {
                    Icon(Icons.Default.DateRange, contentDescription = "Select date")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { showDatePicker = true }
            )
        }

        OutlinedTextField(
            value = birthdayMessage,
            onValueChange = { birthdayMessage = it },
            label = { Text("Birthday message")},
            singleLine = false,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    val birthdayInMillis = datePickerState.selectedDateMillis

                    if (name.isNotBlank() && phoneNumber.isNotBlank() && selectedDate != null) {
                        viewModel.updateFriend(
                            id = friend.id,
                            name = name,
                            phoneNumber = phoneNumber,
                            birthday = birthdayInMillis!!,
                            message = birthdayMessage
                        )

                        if (!uiState.isNameEmpty && !uiState.isPhoneNumberEmpty && !uiState.isBirthdayEmpty) navController.navigateUp() }
                }
            )
        )

        Button(
            onClick = {
                val birthdayInMillis = datePickerState.selectedDateMillis

                viewModel.updateFriend(
                    id = friend.id,
                    name = name,
                    phoneNumber = phoneNumber,
                    birthday = birthdayInMillis!!,
                    message = birthdayMessage
                )

                if (!uiState.isNameEmpty && !uiState.isPhoneNumberEmpty && !uiState.isBirthdayEmpty) navController.navigateUp()
            },
            enabled = name.isNotBlank() && phoneNumber.isNotBlank() && selectedDate != null,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.DarkGray)
        ) {
            Text("Save")
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}