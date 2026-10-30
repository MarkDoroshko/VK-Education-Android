package com.example.vkeducation.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.vkeducation.R
import com.example.vkeducation.presentation.ui.theme.VKEducationTheme

@Composable
fun ErrorState(
    onRepeat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.error_title),
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Button(
            onClick = onRepeat
        ) {
            Text(
                text = stringResource(R.string.repeat_on_error)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewAppDetailsError() {
    VKEducationTheme {
        ErrorState(onRepeat = {})
    }
}