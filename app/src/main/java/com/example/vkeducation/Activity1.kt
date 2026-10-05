package com.example.vkeducation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns.PHONE
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.vkeducation.ui.theme.VKEducationTheme

class Activity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp)
                ) { innerPadding ->
                    ScreenContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        onOpenActivity2 = { text ->
                            val intent = Intent(this@Activity1, Activity2::class.java).apply {
                                putExtra(Activity2.EXTRA_TEXT, text)
                            }
                            startActivity(intent)
                        },
                        onOpenDialApp = { phoneNumber ->
                            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                data = "tel:$phoneNumber".toUri()
                            }
                            if (dialIntent.resolveActivity(packageManager) != null) {
                                startActivity(dialIntent)
                            } else {
                                Toast.makeText(this@Activity1, "Нет приложения для звонков", Toast.LENGTH_SHORT).show()
                            }
                        },
                        onShareText = { text ->
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, text)
                            }
                            startActivity(Intent.createChooser(sendIntent, "Поделиться через…"))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenContent(
    onOpenActivity2: (String) -> Unit,
    onOpenDialApp: (String) -> Unit,
    onShareText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf("") }
    var errorState by remember { mutableStateOf("") }

    fun submit(action: (String) -> Unit) {
        if (textState.isBlank()) {
            errorState = "Поле не должно быть пустым"
        } else {
            errorState = ""
            action(textState)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { newText ->
                textState = newText
            },
            placeholder = { Text("Введите какой-то текст") },
            label = { Text("EditText") },
            singleLine = true,
            isError = errorState.isNotEmpty(),
            supportingText = { Text(errorState) }
        )

        Button(onClick = { submit(onOpenActivity2) }) { Text("Открыть вторую Activity") }

        Button(
            onClick = {
                if (PHONE.matcher(textState).matches()) {
                    submit(onOpenDialApp)
                    errorState = ""
                } else {
                    errorState = "Невалидный формат номера"
                }
            }
        ) { Text("Позвонить другу") }

        Button(onClick = { submit(onShareText) }) { Text("Поделиться текстом") }
    }
}