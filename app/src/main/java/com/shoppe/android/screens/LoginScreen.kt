package com.shoppe.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shoppe.android.ui.components.StatusBarSpacer
import com.shoppe.android.ui.theme.ShoppePrimary
import com.shoppe.android.ui.theme.ShoppeBackground

// Figma CDN images for Login
const val FIGMA_BUBBLE_01_URL = "https://www.figma.com/api/mcp/asset/0b8be41b-3d9b-48f4-bd9e-69ba790da851"
const val FIGMA_BUBBLE_02_URL = "https://www.figma.com/api/mcp/asset/6c8a40f1-8a2f-4b7b-b014-8379add128a0"
const val FIGMA_BUBBLE_03_URL = "https://www.figma.com/api/mcp/asset/79d21f8c-b8a6-4373-8c38-151deb8dfe68"
const val FIGMA_BUBBLE_04_URL = "https://www.figma.com/api/mcp/asset/3ce91139-76b7-45fa-be4c-170fb6bfade0"
const val FIGMA_HEART_URL = "https://www.figma.com/api/mcp/asset/430bc540-bdcd-4957-a65e-523bebd9058a"

@Composable
fun LoginPage(
    onNavigateBack: () -> Unit,
    onNavigateToPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background bubbles decoration
        AsyncImage(
            model = FIGMA_BUBBLE_01_URL,
            contentDescription = "Background bubble 1",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_02_URL,
            contentDescription = "Background bubble 2",
            modifier = Modifier
                .size(350.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_03_URL,
            contentDescription = "Background bubble 3",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_04_URL,
            contentDescription = "Background bubble 4",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarPadding()
                .padding(horizontal = 20.dp)
        ) {
            // Header with Cancel button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF202020),
                    lineHeight = 26.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable(onClick = onNavigateBack)
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            // Login title
            Text(
                text = "Login",
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF202020),
                lineHeight = 54.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Good to see you back with heart
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Good to see you back!",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF202020),
                    lineHeight = 35.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    model = FIGMA_HEART_URL,
                    contentDescription = "Heart",
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = ""
                },
                placeholder = {
                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        color = Color(0xFFD2D2D2)
                    )
                },
                singleLine = true,
                isError = emailError.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        // Validate email and proceed
                        if (email.isBlank()) {
                            emailError = "Please enter your email"
                        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches) {
                            emailError = "Please enter a valid email address"
                        } else {
                            onNavigateToPassword()
                        }
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(60.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = ShoppeBackground,
                    focusedContainerColor = ShoppeBackground,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )

            if (emailError.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = emailError,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Next button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(61.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(ShoppePrimary)
                    .clickable {
                        // Validate email and proceed
                        if (email.isBlank()) {
                            emailError = "Please enter your email"
                        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches) {
                            emailError = "Please enter a valid email address"
                        } else {
                            onNavigateToPassword()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Next",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFFF3F3F3),
                    lineHeight = 31.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Cancel link (duplicate from header for symmetry)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF202020),
                    lineHeight = 26.sp,
                    modifier = Modifier.clickable(onClick = onNavigateBack)
                )
            }
        }
    }
}

@Composable
fun LoginPagePreview() {
    ShoppeAndroidTwoTheme {
        LoginPage(
            onNavigateBack = {},
            onNavigateToPassword = {}
        )
    }
}
