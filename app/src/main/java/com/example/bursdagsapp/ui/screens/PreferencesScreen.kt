package com.example.bursdagsapp.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsapp.data.PreferenceManager
import com.example.bursdagsapp.ui.viewmodels.MyViewModel
import kotlinx.coroutines.launch

@Composable
fun PreferencesScreen(
    myViewModel: MyViewModel = viewModel()
) {
    val context = LocalContext.current

    val snackBareHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var hasSmsPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
    }

    val isSwitchEnabled by myViewModel.isSmsEnabled.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasSmsPermission = isGranted

        if (isGranted) {
            Log.d("SMS", "Permission granted by user")
            myViewModel.start()
        } else {
            Log.d("SMS", "Permission denied by user")
            myViewModel.stop()
        }
    }

    val preferenceManager = remember { PreferenceManager(context) }
    var defaultMessage by remember { mutableStateOf(preferenceManager.getDefaultMessage()) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBareHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Preferences", style = MaterialTheme.typography.displayLarge, modifier = Modifier.padding(bottom = 16.dp))


            Text(text = "Allow app to send SMS: ", style = MaterialTheme.typography.titleLarge)

            Switch(
                modifier = Modifier.scale(1.2f),
                checked = isSwitchEnabled,
                onCheckedChange = { desiredState ->
                    if (desiredState) {
                        if (hasSmsPermission) {
                            myViewModel.start()
                            scope.launch { snackBareHostState.showSnackbar("SMS service enabled") }
                        } else {
                            permissionLauncher.launch(Manifest.permission.SEND_SMS)
                        }
                    } else {
                        myViewModel.stop()
                        scope.launch { snackBareHostState.showSnackbar("SMS service disabled") }
                    }
                },
                thumbContent = if (isSwitchEnabled) {
                    {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                            tint = Color.DarkGray
                        )
                    }
                } else {
                    {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.LightGray,
                    checkedTrackColor = Color.DarkGray,
                    uncheckedThumbColor = Color.Gray,
                    uncheckedTrackColor = Color.DarkGray,
                )
            )


            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Default birthday message:", style = MaterialTheme.typography.titleLarge)
            OutlinedTextField(
                value = defaultMessage,
                onValueChange = { defaultMessage = it },
                singleLine = false,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Button(
                onClick = {
                    preferenceManager.setDefaultMessage(defaultMessage)
                    scope.launch {
                        snackBareHostState.showSnackbar("Default message saved!")
                    }
                },
                colors = ButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.White, disabledContentColor = Color.Gray, disabledContainerColor = Color.DarkGray)
            ) {
                Text("Save")
            }
        }
    }


}