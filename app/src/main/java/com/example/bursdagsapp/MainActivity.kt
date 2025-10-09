package com.example.bursdagsapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.bursdagsapp.ui.theme.BursdagsAppTheme

class MainActivity : ComponentActivity() {

    private val beOmSmsTillatelse = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted: Boolean ->
            if (isGranted) {
                Log.d("SMS", "Tillatelse til å sende SMS")
            } else {
                Log.d("SMS", "Ikke tillatelse til å sende SMS")
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("SMS", "Tillatelse til å sende SMS")
        } else {
            beOmSmsTillatelse.launch(Manifest.permission.SEND_SMS)
        }

        enableEdgeToEdge()
        setContent {
            BursdagsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BursdagsAppTheme {
        Greeting("Android")
    }
}