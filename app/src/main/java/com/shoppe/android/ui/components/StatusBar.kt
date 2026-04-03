package com.shoppe.android.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusBarSpacer() {
    Spacer(modifier = Modifier.statusBarsPadding())
}

@Composable
fun VerticalSpacer(height: Int = 16) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun HorizontalDivider() {
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = Color.LightGray,
        thickness = 1.dp
    )
}
