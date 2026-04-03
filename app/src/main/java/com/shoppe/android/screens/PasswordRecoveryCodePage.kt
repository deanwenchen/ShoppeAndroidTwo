package com.shoppe.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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

// Figma CDN images for Password Recovery Code page
const val FIGMA_BUBBLE_01_CODE_URL = "https://www.figma.com/api/mcp/asset/f705e181-0fc8-4f5a-9d39-9ff7f0d73828"
const val FIGMA_BUBBLE_02_CODE_URL = "https://www.figma.com/api/mcp/asset/34475086-d061-4615-8f49-52d895a2fc18"
const val FIGMA_AVATAR_CODE_URL = "https://www.figma.com/api/mcp/asset/652e96bb-feae-479b-b1cf-be79a1743659"
const val FIGMA_ELLIPSE_CODE_URL = "https://www.figma.com/api/mcp/asset/fe005cb7-1bc1-4cac-9804-a4bb6a787f01"

@Composable
fun PasswordRecoveryCodePage(
    onNavigateBack: () -> Unit,
    onCodeVerified: () -> Unit,
    onResendCode: () -> Unit
) {
    var verificationCode by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background bubbles decoration
        AsyncImage(
            model = FIGMA_BUBBLE_01_CODE_URL,
            contentDescription = "Background bubble 1",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_02_CODE_URL,
            contentDescription = "Background bubble 2",
            modifier = Modifier
                .size(350.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarPadding()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar image in circle
            item {
                Spacer(modifier = Modifier.height(100.dp))

                Box(
                    modifier = Modifier
                        .size(91.dp)
                        .clip(CircleShape)
                        .border(4.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = FIGMA_AVATAR_CODE_URL,
                        contentDescription = "User avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Password Recovery title
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Password Recovery",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202020),
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Instruction text
            item {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Enter 4-digits code we sent you on\nyour phone number",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    lineHeight = 27.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Phone number display
            item {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "+98*******00",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    lineHeight = 25.sp,
                    textAlign = TextAlign.Center
                )
            }

            // 4-digit verification code input boxes
            item {
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(50.dp)
                ) {
                    for (i in 0 until 4) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isError) Color(0xFFFFEBEB) else Color(0xFFF5F5F5)
                                )
                                .border(
                                    if (isError) 2.dp else 0.dp,
                                    if (isError) Color(0xFFFF5790) else Color.Transparent,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (i < verificationCode.length) {
                                // Filled dot
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isError) Color(0xFFFF5790) else Color.Black
                                        )
                                )
                            }
                        }
                    }
                }

                // Hidden text field for code input
                androidx.compose.material3.OutlinedTextField(
                    value = verificationCode,
                    onValueChange = { newValue ->
                        if (newValue.length <= 4 && newValue.all { it.isDigit() }) {
                            verificationCode = newValue
                            isError = false

                            // Auto-submit when 4 digits entered
                            if (newValue.length == 4) {
                                // TODO: Replace with actual verification logic
                                val isValidCode = newValue == "1234"
                                if (isValidCode) {
                                    onCodeVerified()
                                } else {
                                    isError = true
                                }
                            }
                        }
                    },
                    modifier = Modifier.size(0.dp),
                    enabled = true,
                    readOnly = false
                )
            }

            // Send Again button
            item {
                Spacer(modifier = Modifier.height(100.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFFF5790))
                        .clickable {
                            onResendCode()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Send Again",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFF3F3F3),
                        lineHeight = 31.sp
                    )
                }
            }

            // Cancel link
            item {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Cancel",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF202020),
                    lineHeight = 26.sp,
                    modifier = Modifier.clickable(onClick = onNavigateBack)
                )

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun PasswordRecoveryCodePagePreview() {
    com.shoppe.android.ui.theme.ShoppeAndroidTwoTheme {
        PasswordRecoveryCodePage(
            onNavigateBack = {},
            onCodeVerified = {},
            onResendCode = {}
        )
    }
}
