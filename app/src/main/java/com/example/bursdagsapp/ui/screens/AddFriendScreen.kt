package com.example.bursdagsapp.ui.screens

import com.example.bursdagsapp.R
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendScreen(
    viewModel: FriendViewModel,
    navController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var isNameEmpty by remember { mutableStateOf(false) }

    var phoneNumber by remember { mutableStateOf("") }
    var isPhoneNumberEmpty by remember { mutableStateOf(false) }

    var birthdayMessage by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis
    var isSelectedDateEmpty by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add new friend", style = MaterialTheme.typography.displayLarge)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )

        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it},
            label = { Text(stringResource(R.string.phoneNumber_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Box{
            OutlinedTextField(
                value = if (selectedDate != null) {
                    SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date(selectedDate))
                } else {
                   stringResource(R.string.datePicker_empty)
                },
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.birthday_label)) },
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
            label = { Text(stringResource(R.string.birthayMessage_label))},
            singleLine = false,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (name.isNotBlank() && phoneNumber.isNotBlank() && selectedDate != null) {
                        viewModel.addFriend(
                            name = name,
                            phoneNumber = phoneNumber,
                            birthday = selectedDate,
                            message = birthdayMessage
                        )

                        if (!uiState.isNameEmpty && !uiState.isPhoneNumberEmpty && !uiState.isBirthdayEmpty) navController.navigateUp() }
                    }
            )
        )

        Button(
            onClick = {
                viewModel.addFriend(
                    name = name,
                    phoneNumber = phoneNumber,
                    birthday = selectedDate!!,
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
            Text(stringResource(R.string.save_friend))
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.datePicker_confirm))
                }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.datePicker_dismiss))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}