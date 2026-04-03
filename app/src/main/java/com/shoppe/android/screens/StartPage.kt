package com.shoppe.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shoppe.android.ui.components.StatusBarSpacer
import com.shoppe.android.ui.theme.ShoppePrimary

// Figma CDN images
const val FIGMA_LOGO_URL = "https://www.figma.com/api/mcp/asset/e1553fc8-89d8-4590-9294-92b80bf32f55"
const val FIGMA_ARROW_URL = "https://www.figma.com/api/mcp/asset/e522c39b-f444-4ccf-aec2-33eda3f6249c"

@Composable
fun StartPage(
    onNavigateToCreateAccount: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Status bar spacer
            StatusBarSpacer()

            // Main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo - centered vertically
                Spacer(modifier = Modifier.weight(1f))

                // Logo image in circle
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = FIGMA_LOGO_URL,
                        contentDescription = "Shoppe Logo",
                        modifier = Modifier.size(120.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Shoppe title
                Text(
                    text = "Shoppe",
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202020),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Tagline
                Text(
                    text = "Beautiful eCommerce UI Kit\nfor your online store",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF202020),
                    textAlign = TextAlign.Center,
                    lineHeight = 33.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                // Let's get started button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(61.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(ShoppePrimary)
                        .clickable(onClick = onNavigateToCreateAccount),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Let's get started",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFF3F3F3),
                        lineHeight = 31.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // I already have an account
                Row(
                    modifier = Modifier
                        .clickable(onClick = onNavigateToLogin)
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "I already have an account",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF202020),
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Arrow icon
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = FIGMA_ARROW_URL,
                            contentDescription = "Arrow",
                            modifier = Modifier.size(16.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            // Status bar icons (optional decorative element)
            // Can be added if needed for full design match
        }
    }
}

@Composable
fun StartPagePreview() {
    ShoppeAndroidTwoTheme {
        StartPage(
            onNavigateToCreateAccount = {},
            onNavigateToLogin = {}
        )
    }
}
