package com.shoppe.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shoppe.android.ui.components.StatusBarSpacer
import com.shoppe.android.ui.theme.ShoppePrimary

// Sample product data
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val image: String,
    val category: String
)

val sampleProducts = listOf(
    Product("1", "Classic White Sneakers", 89.99, "https://images.unsplash.com/photo-1549298916-b41d501d3772", "Shoes"),
    Product("2", "Leather Crossbody Bag", 129.99, "https://images.unsplash.com/photo-1548036328-c9fa89d128fa", "Bags"),
    Product("3", "Minimalist Watch", 199.99, "https://images.unsplash.com/photo-1523275335684-37898b6baf30", "Accessories"),
    Product("4", "Denim Jacket", 79.99, "https://images.unsplash.com/photo-1523275335684-37898b6baf30", "Clothing"),
    Product("5", "Summer Dress", 59.99, "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1", "Clothing"),
    Product("6", "Ankle Boots", 119.99, "https://images.unsplash.com/photo-1543163521-1bf539c55dd2", "Shoes")
)

val categories = listOf("All", "Shoes", "Bags", "Accessories", "Clothing", "Jewelry")

@Composable
fun ShopPage() {
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarPadding()
        ) {
            // Header with title and cart icon
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Shop",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF202020)
                    )
                    // Cart icon placeholder
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF5F5F5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🛒",
                            fontSize = 18.sp
                        )
                    }
                }
            }

            // Search bar
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF5F5F5))
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "🔍 Search products...",
                        fontSize = 15.sp,
                        color = Color(0xFF999999)
                    )
                }
            }

            // Categories
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Categories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202020),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier.height(140.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(categories) { category ->
                        CategoryItem(
                            name = category,
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }
            }

            // Featured Products
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Featured Products",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202020),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Products grid
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    items(sampleProducts) { product ->
                        ProductCard(product = product)
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun CategoryItem(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(
                    if (isSelected) 2.dp else 0.dp,
                    if (isSelected) ShoppePrimary else Color.Transparent,
                    CircleShape
                )
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (name) {
                    "All" -> "🏪"
                    "Shoes" -> "👟"
                    "Bags" -> "👜"
                    "Accessories" -> "⌚"
                    "Clothing" -> "👕"
                    "Jewelry" -> "💍"
                    else -> "📦"
                },
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = name,
            fontSize = 12.sp,
            color = if (isSelected) ShoppePrimary else Color(0xFF666666),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun ProductCard(product: Product) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Product image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Product name
        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF202020),
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Product price
        Text(
            text = "$${product.price}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = ShoppePrimary
        )
    }
}

@Composable
fun ShopPagePreview() {
    com.shoppe.android.ui.theme.ShoppeAndroidTwoTheme {
        ShopPage()
    }
}
