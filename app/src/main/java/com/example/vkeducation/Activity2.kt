package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.vkeducation.ui.theme.VKEducationTheme

class Activity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // intent доступен из ComponentActivity
        val text = intent.getStringExtra(EXTRA_TEXT).orEmpty()

        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                CenterText(text)
            }
        }
    }

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }
}

@Composable
fun CenterText(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 30.sp
        )
    }
}

@Preview
@Composable
fun CenterTextPreview() {
    VKEducationTheme {
        CenterText("Test")
    }
}