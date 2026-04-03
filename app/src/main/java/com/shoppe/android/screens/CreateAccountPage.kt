package com.shoppe.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shoppe.android.ui.components.StatusBarSpacer
import com.shoppe.android.ui.theme.ShoppePrimary
import com.shoppe.android.ui.theme.ShoppeBackground
import com.shoppe.android.ui.theme.ShoppeOnSurface

// Figma CDN images for Create Account
const val FIGMA_BUBBLES_URL = "https://www.figma.com/api/mcp/asset/0c63e615-1cb5-4db7-b4d3-7ed9691a26ed"
const val FIGMA_UPLOAD_PHOTO_URL = "https://www.figma.com/api/mcp/asset/c373a992-46e4-47c0-94dc-997cfc8fb9d6"
const val FIGMA_CAMERA_ICON_URL = "https://www.figma.com/api/mcp/asset/12282f0c-78d9-447a-a674-4318bb090104"
const val FIGMA_ENGLAND_FLAG_URL = "https://www.figma.com/api/mcp/asset/fd73df22-8fed-4f46-8bb8-39dd6737ed05"
const val FIGMA_ARROW_DOWN_URL = "https://www.figma.com/api/mcp/asset/5e85c4d6-acbf-45f9-942b-6a0d3feff516"
const val FIGMA_EYE_SLASH_URL = "https://www.figma.com/api/mcp/asset/cf8ef12b-01b0-4b20-99b5-f04d6deb4e78"

@Composable
fun CreateAccountPage(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }

    val validateForm: () -> Boolean = {
        var isValid = true

        // Validate email
        if (email.isBlank()) {
            emailError = "Please enter your email"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches) {
            emailError = "Please enter a valid email address"
            isValid = false
        } else {
            emailError = ""
        }

        // Validate password
        if (password.isBlank()) {
            passwordError = "Please enter a password"
            isValid = false
        } else if (password.length < 8) {
            passwordError = "Password must be at least 8 characters"
            isValid = false
        } else {
            passwordError = ""
        }

        // Validate phone (optional field, but if provided, validate format)
        if (phoneNumber.isNotBlank() && phoneNumber.length < 7) {
            phoneError = "Please enter a valid phone number"
            isValid = false
        } else {
            phoneError = ""
        }

        isValid
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background bubbles decoration
        Image(
            model = FIGMA_BUBBLES_URL,
            contentDescription = "Background decoration",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarPadding()
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                // Cancel button
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                item {
                    Text(
                        text = "Create\nAccount",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF202020),
                        lineHeight = 54.sp,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                }

                // Upload Photo
                item {
                    Spacer(modifier = Modifier.height(40.dp))

                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .border(
                                2.dp,
                                ShoppePrimary.copy(alpha = 0.5f),
                                CircleShape
                            )
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = FIGMA_UPLOAD_PHOTO_URL,
                            contentDescription = "Upload photo",
                            modifier = Modifier.fillMaxSize()
                        )
                        AsyncImage(
                            model = FIGMA_CAMERA_ICON_URL,
                            contentDescription = "Camera icon",
                            modifier = Modifier.size(34.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                // Form fields
                item {
                    Spacer(modifier = Modifier.height(40.dp))

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
                        isError = emailError.isNotEmpty(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
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
                        Text(
                            text = emailError,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    // Password field
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = ""
                        },
                        placeholder = {
                            Text(
                                text = "Password",
                                fontSize = 14.sp,
                                color = Color(0xFFD2D2D2)
                            )
                        },
                        isError = passwordError.isNotEmpty(),
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                AsyncImage(
                                    model = FIGMA_EYE_SLASH_URL,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                    modifier = Modifier.size(16.dp),
                                    colorFilter = if (passwordVisible) ColorFilter.tint(ShoppePrimary) else null
                                )
                            }
                        },
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
                    if (passwordError.isNotEmpty()) {
                        Text(
                            text = passwordError,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    // Phone number field with country flag
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            phoneError = ""
                        },
                        placeholder = {
                            Text(
                                text = "Your number",
                                fontSize = 14.sp,
                                color = Color(0xFFD2D2D2)
                            )
                        },
                        isError = phoneError.isNotEmpty(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        ),
                        leadingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                // UK Flag
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = FIGMA_ENGLAND_FLAG_URL,
                                        contentDescription = "UK Flag",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                // Dropdown arrow
                                AsyncImage(
                                    model = FIGMA_ARROW_DOWN_URL,
                                    contentDescription = "Country selector",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        },
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
                    if (phoneError.isNotEmpty()) {
                        Text(
                            text = phoneError,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(48.dp))

                    // Done button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(61.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(ShoppePrimary)
                            .clickable {
                                // Validate form before proceeding
                                if (validateForm()) {
                                    onNavigateToLogin()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Done",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Light,
                            color = Color(0xFFF3F3F3),
                            lineHeight = 31.sp
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Composable
fun CreateAccountPagePreview() {
    ShoppeAndroidTwoTheme {
        CreateAccountPage(
            onNavigateBack = {},
            onNavigateToLogin = {}
        )
    }
}
