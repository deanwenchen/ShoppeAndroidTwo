package com.shoppe.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

// Figma CDN images for Hello Card page
const val FIGMA_BUBBLE_01_HELLO_URL = "https://www.figma.com/api/mcp/asset/11cae639-4815-494c-88e9-bf0534db8a52"
const val FIGMA_BUBBLE_02_HELLO_URL = "https://www.figma.com/api/mcp/asset/20505f9d-558f-4e5d-916c-5719cbeffadc"
const val FIGMA_HELLO_IMAGE_URL = "https://www.figma.com/api/mcp/asset/d19219eb-8cb6-4ee4-bf2d-2bf2123e957a"
const val FIGMA_HELLO_MASK_URL = "https://www.figma.com/api/mcp/asset/b0814ad1-8f24-47bf-8cf0-9bfcc84fa391"

// Onboarding slide data
data class OnboardingSlide(
    val image: String,
    val title: String,
    val description: String
)

val onboardingSlides = listOf(
    OnboardingSlide(
        image = FIGMA_HELLO_IMAGE_URL,
        title = "Hello",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non consectetur turpis. Morbi eu eleifend lacus."
    ),
    OnboardingSlide(
        image = FIGMA_HELLO_IMAGE_URL,
        title = "Discover",
        description = "Discover amazing products tailored to your style. Shop smarter with personalized recommendations."
    ),
    OnboardingSlide(
        image = FIGMA_HELLO_IMAGE_URL,
        title = "Enjoy",
        description = "Enjoy fast shipping, secure payments, and excellent customer support. Your satisfaction is our priority."
    ),
    OnboardingSlide(
        image = FIGMA_HELLO_IMAGE_URL,
        title = "Shop",
        description = "Start shopping now and experience the future of online shopping. Best deals await you."
    )
)

@Composable
fun HelloCardPage(
    onNavigateToShop: () -> Unit
) {
    var currentSlide by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background bubbles decoration
        AsyncImage(
            model = FIGMA_BUBBLE_01_HELLO_URL,
            contentDescription = "Background bubble 1",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )
        AsyncImage(
            model = FIGMA_BUBBLE_02_HELLO_URL,
            contentDescription = "Background bubble 2",
            modifier = Modifier
                .size(350.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Main card with image and content
            item {
                Spacer(modifier = Modifier.height(80.dp))

                // Card container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 280.dp), // Space for text content below
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Image with rounded corners
                        AsyncImage(
                            model = onboardingSlides[currentSlide].image,
                            contentDescription = "Onboarding image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(338.dp)
                                .clip(RoundedCornerShape(30.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Title and description below image
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Hello title
                        Text(
                            text = onboardingSlides[currentSlide].title,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF202020),
                            lineHeight = 36.sp,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Description text
                        Text(
                            text = onboardingSlides[currentSlide].description,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.Black,
                            lineHeight = 27.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Pagination dots
            item {
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.height(20.dp)
                ) {
                    for (i in onboardingSlides.indices) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (i == currentSlide) ShoppePrimary else Color(0xFFD0D0D0)
                                )
                        )
                    }
                }
            }

            // Next/Skip button
            item {
                Spacer(modifier = Modifier.height(60.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(61.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(ShoppePrimary)
                        .clickable {
                            if (currentSlide < onboardingSlides.lastIndex) {
                                currentSlide++
                            } else {
                                onNavigateToShop()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (currentSlide < onboardingSlides.lastIndex) "Next" else "Get Started",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFF3F3F3),
                        lineHeight = 31.sp
                    )
                }
            }

            // Skip link
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Skip",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF202020),
                    lineHeight = 26.sp,
                    modifier = Modifier.clickable(onClick = onNavigateToShop)
                )

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun HelloCardPagePreview() {
    com.shoppe.android.ui.theme.ShoppeAndroidTwoTheme {
        HelloCardPage(
            onNavigateToShop = {}
        )
    }
}
