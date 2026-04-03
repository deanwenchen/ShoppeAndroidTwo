package com.shoppe.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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

// Figma CDN images for New Password page
const val FIGMA_BUBBLE_01_NEWPWD_URL = "https://www.figma.com/api/mcp/asset/823890c4-f218-4a19-a42d-bd4ac4439c35"
const val FIGMA_BUBBLE_02_NEWPWD_URL = "https://www.figma.com/api/mcp/asset/4236a735-b455-4aa4-a113-658321b0ac2a"
const val FIGMA_AVATAR_NEWPWD_URL = "https://www.figma.com/api/mcp/asset/9d9fa3aa-fa1f-42f6-a77b-20ad22fb1bab"

@Composable
fun NewPasswordPage(
    onNavigateBack: () -> Unit,
    onPasswordResetSuccess: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background bubbles decoration
        AsyncImage(
            model = FIGMA_BUBBLE_01_NEWPWD_URL,
            contentDescription = "Background bubble 1",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_02_NEWPWD_URL,
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
                        model = FIGMA_AVATAR_NEWPWD_URL,
                        contentDescription = "User avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Setup New Password title
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Setup New Password",
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
                    text = "Please, setup a new password for\nyour account",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    lineHeight = 27.sp,
                    textAlign = TextAlign.Center
                )
            }

            // New Password input field
            item {
                Spacer(modifier = Modifier.height(50.dp))

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = {
                        newPassword = it
                        isError = false
                        errorMessage = ""
                    },
                    placeholder = {
                        Text(
                            text = "New Password",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFDCDCDC)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .background(Color(0xFFF8F8F8)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF8F8F8),
                        unfocusedContainerColor = Color(0xFFF8F8F8),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )
            }

            // Repeat Password input field
            item {
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        isError = false
                        errorMessage = ""
                    },
                    placeholder = {
                        Text(
                            text = "Repeat Password",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFDCDCDC)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .background(Color(0xFFF8F8F8)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF8F8F8),
                        unfocusedContainerColor = Color(0xFFF8F8F8),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )
            }

            // Save button
            item {
                Spacer(modifier = Modifier.height(60.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(61.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(ShoppePrimary)
                        .clickable {
                            // Validate passwords match
                            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                                isError = true
                                errorMessage = "Please fill in all fields"
                                return@clickable
                            }
                            if (newPassword.length < 8) {
                                isError = true
                                errorMessage = "Password must be at least 8 characters"
                                return@clickable
                            }
                            if (newPassword != confirmPassword) {
                                isError = true
                                errorMessage = "Passwords do not match"
                                return@clickable
                            }
                            onPasswordResetSuccess()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Save",
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
fun NewPasswordPagePreview() {
    com.shoppe.android.ui.theme.ShoppeAndroidTwoTheme {
        NewPasswordPage(
            onNavigateBack = {},
            onPasswordResetSuccess = {}
        )
    }
}
