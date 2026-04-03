package com.shoppe.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shoppe.android.ui.components.StatusBarSpacer
import com.shoppe.android.ui.theme.ShoppePrimary
import com.shoppe.android.ui.theme.ShoppeBackground
import com.shoppe.android.ui.theme.ShoppeError

// Password input mode enumeration
enum class PasswordMode {
    FOUR_DIGIT,
    EIGHT_DIGIT
}

// Figma CDN images for Password Page
const val FIGMA_BUBBLE_01_PWD_URL = "https://www.figma.com/api/mcp/asset/cdb7418b-f574-4a5e-bff7-9efa5f3bfcbf"
const val FIGMA_BUBBLE_02_PWD_URL = "https://www.figma.com/api/mcp/asset/16be0904-9e40-4869-9ef7-9553e1d67b6b"
const val FIGMA_AVATAR_URL = "https://www.figma.com/api/mcp/asset/6c30470d-1fdf-4caa-b8ca-76180b708e99"
const val FIGMA_ARROW_RIGHT_URL = "https://www.figma.com/api/mcp/asset/4c4eb28f-06d9-4e0d-81b9-44a5d5466343"

@Composable
fun PasswordPage(
    onNavigateBack: () -> Unit,
    onLoginSuccess: () -> Unit,
    onForgotPassword: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var passwordMode by remember { mutableStateOf(PasswordMode.FOUR_DIGIT) }
    var isError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background bubbles decoration
        AsyncImage(
            model = FIGMA_BUBBLE_01_PWD_URL,
            contentDescription = "Background bubble 1",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_02_PWD_URL,
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
                        model = FIGMA_AVATAR_URL,
                        contentDescription = "User avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Hello, Romina!!
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Hello, Romina!!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202020),
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Type your password
            item {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Type your password",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    lineHeight = 35.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Password input boxes
            item {
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.height(51.dp)
                ) {
                    // Create 4 boxes for 4-digit mode or 8 boxes for 8-digit mode
                    val boxCount = if (passwordMode == PasswordMode.FOUR_DIGIT) 4 else 8
                    for (i in 0 until boxCount) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (isError) ShoppeError.copy(alpha = 0.1f) else ShoppeBackground
                                )
                                .border(
                                    if (isError) 2.dp else 0.dp,
                                    if (isError) ShoppeError else Color.Transparent,
                                    RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (i < password.length) {
                                // Filled dot
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isError) ShoppeError else Color.Black
                                        )
                                )
                            }
                        }
                    }
                }

                // Hidden text field for password input
                OutlinedTextField(
                    value = password,
                    onValueChange = { newValue ->
                        if (newValue.length <= 8) {
                            password = newValue
                            isError = false

                            // Switch to 8-digit mode when user starts typing
                            if (passwordMode == PasswordMode.FOUR_DIGIT && newValue.isNotEmpty()) {
                                passwordMode = PasswordMode.EIGHT_DIGIT
                            }

                            // Auto-submit when 8 digits entered
                            if (newValue.length == 8 && passwordMode == PasswordMode.EIGHT_DIGIT) {
                                // TODO: Replace with actual authentication logic
                                // For demo purposes, accept "12345678" as valid password
                                val isValidPassword = newValue == "12345678"
                                if (isValidPassword) {
                                    onLoginSuccess()
                                } else {
                                    isError = true
                                }
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.size(0.dp),
                    enabled = true,
                    readOnly = false
                }
            }

            // Not you? link with arrow or Forgot your password?
            item {
                Spacer(modifier = Modifier.weight(1f))

                if (isError) {
                    // Show Forgot your password? link when error
                    Text(
                        text = "Forgot your password?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = ShoppePrimary,
                        lineHeight = 26.sp,
                        modifier = Modifier
                            .clickable(onClick = onForgotPassword)
                            .padding(vertical = 16.dp)
                    )
                } else {
                    // Show Not you? link when no error
                    Row(
                        modifier = Modifier
                            .clickable(onClick = onNavigateBack)
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Not you?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            color = Color(0xFF202020),
                            lineHeight = 26.sp
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // Arrow button
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(ShoppePrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = FIGMA_ARROW_RIGHT_URL,
                                contentDescription = "Arrow",
                                modifier = Modifier.size(16.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun PasswordPagePreview() {
    ShoppeAndroidTwoTheme {
        PasswordPage(
            onNavigateBack = {},
            onLoginSuccess = {},
            onForgotPassword = {}
        )
    }
}
