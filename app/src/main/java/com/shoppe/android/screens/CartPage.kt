package com.shoppe.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.shoppe.android.ui.theme.ShoppePrimary
import com.shoppe.android.ui.components.QuantitySelector
import com.shoppe.android.ui.components.StatusBarSpacer
import com.shoppe.android.viewmodels.CartViewModel

@Composable
fun CartPage(
    viewModel: CartViewModel,
    onNavigateBack: () -> Unit,
    onContinueShopping: () -> Unit
) {
    val state by viewModel.state.collectAsState()

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
            // Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF202020)
                        )
                    }

                    Text(
                        text = "Shopping Cart",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF202020)
                    )

                    // Spacer for balance
                    Spacer(modifier = Modifier.size(40.dp))
                }
            }

            if (state.items.isEmpty()) {
                // Empty Cart State
                item {
                    EmptyCartContent(
                        onContinueShopping = onContinueShopping
                    )
                }
            } else {
                // Cart Items
                items(state.items) { item ->
                    CartItemRow(
                        item = item,
                        onQuantityChange = { newQuantity ->
                            viewModel.updateQuantity(item.productId, newQuantity)
                        },
                        onRemove = {
                            viewModel.removeItem(item.productId)
                        }
                    )
                }

                // Order Summary
                item {
                    OrderSummary(
                        totalItems = state.totalItems,
                        totalPrice = state.totalPrice
                    )
                }

                // Checkout Button
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.Button(
                        onClick = { /* TODO: Navigate to checkout */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = ShoppePrimary
                        )
                    ) {
                        Text(
                            text = "Checkout",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Composable
fun EmptyCartContent(
    onContinueShopping: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "🛒",
            fontSize = 64.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your cart is empty",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF202020)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Add items to get started",
            fontSize = 14.sp,
            color = Color(0xFF999999)
        )

        Spacer(modifier = Modifier.height(32.dp))

        androidx.compose.material3.Button(
            onClick = onContinueShopping,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = ShoppePrimary
            )
        ) {
            Text(
                text = "Continue Shopping",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}

@Composable
fun CartItemRow(
    item: com.shoppe.android.models.CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Product Image
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = item.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Product Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF202020),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "$${String.format("%.2f", item.price)}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ShoppePrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            QuantitySelector(
                quantity = item.quantity,
                onQuantityChange = onQuantityChange
            )
        }

        // Remove Button
        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5))
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove item",
                tint = Color(0xFF666666),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun OrderSummary(
    totalItems: Int,
    totalPrice: Double
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color(0xFFF9F9F9), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Order Summary",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF202020)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total Items",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
            Text(
                text = totalItems.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF202020)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Subtotal",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
            Text(
                text = "$${String.format("%.2f", totalPrice)}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF202020)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color(0xFFE0E0E0)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF202020)
            )
            Text(
                text = "$${String.format("%.2f", totalPrice)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ShoppePrimary
            )
        }
    }
}

@Composable
fun CartPagePreview() {
    val viewModel = remember { CartViewModel() }

    com.shoppe.android.ui.theme.ShoppeAndroidTwoTheme {
        CartPage(
            viewModel = viewModel,
            onNavigateBack = {},
            onContinueShopping = {}
        )
    }
}
