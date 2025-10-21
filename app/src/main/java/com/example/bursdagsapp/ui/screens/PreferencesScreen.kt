package com.example.bursdagsapp.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Switch
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsapp.MyApp
import com.example.bursdagsapp.ui.viewmodels.MyViewModel

@Composable
fun PreferencesScreen(
    myViewModel: MyViewModel = viewModel()
) {
    val context = LocalContext.current

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Preferences", style = MaterialTheme.typography.displayLarge, modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Allow app to send SMS: ", style = MaterialTheme.typography.titleLarge)

            Switch(
                checked = isSwitchEnabled,
                onCheckedChange = { desiredState ->
                    if (desiredState) {
                        if (hasSmsPermission) {
                            myViewModel.start()
                        } else {
                            permissionLauncher.launch(Manifest.permission.SEND_SMS)
                        }
                    } else {
                        myViewModel.stop()
                    }
                },
                thumbContent = if (isSwitchEnabled) {
                    {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
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
                }
            )
        }
    }
}