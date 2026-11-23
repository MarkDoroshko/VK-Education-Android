package com.example.vkeducation.presentation.screen.appdetails.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducation.R
import com.example.vkeducation.presentation.screen.appdetails.InstallState
import com.example.vkeducation.presentation.ui.theme.VKEducationTheme

@Composable
fun InstallButton(
    installState: InstallState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        enabled = installState != InstallState.Installed,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = modifier,
    ) {
        Text(
            text = when (installState) {
                InstallState.NotInstalled -> stringResource(R.string.install)
                is InstallState.Installing -> stringResource(R.string.cancel_install, installState.progress)
                InstallState.Installed -> stringResource(R.string.installed)
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VKEducationTheme {
        InstallButton(
            installState = InstallState.NotInstalled,
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
