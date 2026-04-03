package com.shoppe.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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

// Figma CDN images for Password Recovery
const val FIGMA_BUBBLE_01_RECOVERY_URL = "https://www.figma.com/api/mcp/asset/d7cff6c4-7742-44a7-b2c8-a02954593d23"
const val FIGMA_BUBBLE_02_RECOVERY_URL = "https://www.figma.com/api/mcp/asset/a13d2fe3-f87a-4ac7-a265-1fcdc426b2f2"
const val FIGMA_AVATAR_RECOVERY_URL = "https://www.figma.com/api/mcp/asset/68619146-4a3d-4203-b2d3-6cd0f4b3e567"
const val FIGMA_CHECK_URL = "https://www.figma.com/api/mcp/asset/5cdf8bd0-872f-43a7-a96e-44ca4e490e6c"
const val FIGMA_CHECK_EMPTY_URL = "https://www.figma.com/api/mcp/asset/c382c022-672a-4b55-a510-b30d71f42fe2"

@Composable
fun PasswordRecoveryPage(
    onNavigateBack: () -> Unit,
    onRecoveryMethodSelected: (String) -> Unit
) {
    var selectedMethod by remember { mutableStateOf("sms") } // "sms" or "email"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background bubbles decoration
        AsyncImage(
            model = FIGMA_BUBBLE_01_RECOVERY_URL,
            contentDescription = "Background bubble 1",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_02_RECOVERY_URL,
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
                        model = FIGMA_AVATAR_RECOVERY_URL,
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
                    text = "How you would like to restore\nyour password?",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    lineHeight = 27.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Recovery method options
            item {
                Spacer(modifier = Modifier.height(40.dp))

                // SMS option
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            if (selectedMethod == "sms") {
                                Color(0xFFE5EBFC)
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            if (selectedMethod == "sms") {
                                0.dp
                            } else {
                                1.dp
                            }
                            , Color(0xFFE5EBFC), RoundedCornerShape(18.dp)
                        )
                        .clickable {
                            selectedMethod = "sms"
                            onRecoveryMethodSelected("sms")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "SMS",
                            fontSize = 15.sp,
                            fontWeight = if (selectedMethod == "sms") FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedMethod == "sms") ShoppePrimary else Color.Black,
                            lineHeight = 19.sp
                        )

                        if (selectedMethod == "sms") {
                            AsyncImage(
                                model = FIGMA_CHECK_URL,
                                contentDescription = "Selected",
                                modifier = Modifier.size(20.dp),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            AsyncImage(
                                model = FIGMA_CHECK_EMPTY_URL,
                                contentDescription = "Not selected",
                                modifier = Modifier.size(20.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))

                // Email option
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            if (selectedMethod == "email") {
                                Color(0xFFFFEBEB)
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            if (selectedMethod == "email") {
                                0.dp
                            } else {
                                1.dp
                            }
                            , Color(0xFFFFEBEB), RoundedCornerShape(18.dp)
                        )
                        .clickable {
                            selectedMethod = "email"
                            onRecoveryMethodSelected("email")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Email",
                            fontSize = 15.sp,
                            fontWeight = if (selectedMethod == "email") FontWeight.Medium else FontWeight.Medium,
                            color = if (selectedMethod == "email") Color.Black else Color.Black,
                            lineHeight = 19.sp
                        )

                        if (selectedMethod == "email") {
                            AsyncImage(
                                model = FIGMA_CHECK_URL,
                                contentDescription = "Selected",
                                modifier = Modifier.size(20.dp),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            AsyncImage(
                                model = FIGMA_CHECK_EMPTY_URL,
                                contentDescription = "Not selected",
                                modifier = Modifier.size(20.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(48.dp))

                // Next button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(61.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(ShoppePrimary)
                        .clickable {
                            onRecoveryMethodSelected(selectedMethod)
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
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                // Cancel link
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
fun PasswordRecoveryPagePreview() {
    ShoppeAndroidTwoTheme {
        PasswordRecoveryPage(
            onNavigateBack = {},
            onRecoveryMethodSelected = {}
        )
    }
}
