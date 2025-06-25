package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.ui.product.ProductViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.ecommerceapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material3.*

@Composable
fun OrderTrackingScreen(viewModel: ProductViewModel, onNavigateCart: () -> Unit, onNavigateFavorite: () -> Unit, onNavigateCategory: () -> Unit, onNavigateHome: () -> Unit) {
    val state = viewModel.state.collectAsState().value
    val client = state.client
    val cartItems = viewModel.state.collectAsState().value.orderItems
    val selectedIndex = state.selectedGiftIndex
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    val options = listOf(
        Triple(R.drawable.gift1, R.drawable.package1, "Beige"),
        Triple(R.drawable.gift2, R.drawable.package2, "Black"),
        Triple(R.drawable.gift3, R.drawable.package3, "Purple"),
        Triple(R.drawable.gift4, R.drawable.package4, "Pink")
    )
    val totalQuantity = cartItems.size
    Column(modifier = Modifier.fillMaxSize()) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                .height(50.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "여자_SKIN",
                fontSize = 22.sp,
                fontFamily = customFontFamily,
                color = Color(0xFF907E36),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Tracking >",
                fontSize = 25.sp,
                fontFamily = customFontFamily,
                color = Color(0xFF1D0057)
            )
        }
        Divider(
            color = Color(0xFF1D0057),
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F2F2)),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            "Your Informations:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        val labelStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 18.sp,
                            color = Color(0xFF1D0057),
                            fontWeight = FontWeight.Bold
                        )
                        val valueStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append("Email: ")
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append(client.email)
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append("Name: ")
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append("${client.firstName} ${client.lastName}")
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append("Country: ")
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append(client.country)
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append("Address: ")
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append(client.address)
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append("Postal Code: ")
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append(client.postalCode)
                            }
                        })
                    }
                }
                Divider(
                    color = Color(0xFF1D0057),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
            item{
                Text("Your Package and your gift",  modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)
            }
            item {
                if (selectedIndex != -1) {
                    val (giftRes, packageRes, colorName) = options[selectedIndex]
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = giftRes),
                                contentDescription = "Gift",
                                modifier = Modifier.size(150.dp)
                            )
                            Image(
                                painter = painterResource(id = packageRes),
                                contentDescription = "Package",
                                modifier = Modifier.size(150.dp)
                            )
                        }
                        Text(
                            text = "$colorName Gift and Package",
                            fontSize = 16.sp,
                            color = Color(0xFF907E36),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider(
                            color = Color(0xFF1D0057),
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )
                    }
                }
            }
            item {
                Text("Your products:",  modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)
            }

            items(cartItems) { item ->
                val price = item.product.price.toDoubleOrNull() ?: 0.0
                val discount = item.product.discountPercentage ?: 0
                val discountedPrice = price * (1 - discount / 100.0)
                val imageResId = getImageResIdByName(item.product.imageResId)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = item.product.title,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(end = 12.dp)
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                item.product.title,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${item.quantity} x $${"%.2f".format(discountedPrice)}",
                                fontSize = 14.sp,
                                color = Color(0xFF1D0057)
                            )
                        }
                    }
                    Divider(
                        color = Color(0xFF1D0057),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
            }

            item {
                Text("$totalQuantity items", fontWeight = FontWeight.Medium, fontSize = 18.sp, color = Color(0xFF1D0057))
            }
            item {
                val totalPrice = cartItems.sumOf {
                    val price = it.product.price.toDoubleOrNull() ?: 0.0
                    val discount = it.product.discountPercentage ?: 0
                    val discounted = price * (1 - discount / 100.0)
                    discounted * it.quantity
                }
                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F2F2)),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            "Total: $${"%.2f".format(totalPrice)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF1D0057)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Delivery: $5.50",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF1D0057)
                        )
                    }
                }
                Divider(
                    color = Color(0xFF1D0057),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
            item {
                Text("Order Tracking",  modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)
            }
            item {
                OrderStatusTracker()
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 75.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    androidx.compose.material3.Text(
                        text = "MORE OF OUR SOCIALS",
                        fontSize = 20.sp,
                        color = Color(0xFF1D0057),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    androidx.compose.material3.Divider(
                        color = Color(0xFF1D0057),
                        thickness = 0.5.dp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            item{
                SocialMediaSection()
            }
        }

        val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
        val favoriteCount = viewModel.state.collectAsState().value.products.count { it.isFavorite }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val inactiveColor = Color(0xFF907E36)
            Box(
                modifier = Modifier.size(40.dp)
            ){
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home",
                    tint = inactiveColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { onNavigateHome() }
                )
            }
            Box(modifier = Modifier
                .size(40.dp)
                .clickable { onNavigateFavorite() }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color(0xFF907E36),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
                if (favoriteCount > 0) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Red, CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = if (favoriteCount > 99) "99+" else "$favoriteCount",
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.category),
                contentDescription = "Category",
                tint = Color(0xFF907E36),
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onNavigateCategory() }
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onNavigateCart() }
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color(0xFF907E36),
                    modifier = Modifier
                        .align(Alignment.Center)
                )

                if (totalCartItems > 0) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Red, CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = "$totalCartItems",
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}