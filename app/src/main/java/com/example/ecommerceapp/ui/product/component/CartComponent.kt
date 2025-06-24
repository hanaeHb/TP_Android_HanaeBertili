package com.example.ecommerceapp.ui.product.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Button

import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.CartItems
import com.example.ecommerceapp.ui.product.ProductViewModel
import kotlinx.coroutines.delay

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CartScreen(viewModel: ProductViewModel, onNavigateHome: () -> Unit, onNavigateFavorite: () -> Unit, onNavigateCategory: () -> Unit, onNavigateCheckout: () -> Unit) {
    val cartItems = viewModel.state.collectAsState().value.cartItems
    val col = Color(0xFF907E36)
    val colM = Color(0xFFE6E6FA)
    val fontFamily = FontFamily(Font(R.font.dancingscript))
    val total = cartItems.sumOf {
        val price = it.product.price.toDoubleOrNull() ?: 0.0
        val discount = it.product.discountPercentage ?: 0
        val finalPrice = if (discount > 0) {
            price - (price * discount / 100.0)
        } else {
            price
        }
        finalPrice * it.quantity
    }
    Column(modifier = Modifier
        .fillMaxSize()
    )  {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(50.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "여자_SKIN",
                fontSize = 22.sp,
                fontFamily = fontFamily,
                color = col,
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
                text = "My Cart >",
                fontSize = 25.sp,
                fontFamily = fontFamily,
                color = Color(0xFF1D0057)
            )
        }
        Divider(
            color = Color(0xFF1D0057),
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(modifier = Modifier.weight(1f)) {
            val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
            ) {
                item {
                    Row(modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                        Box(
                            modifier = Modifier
                                .background(colM, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Total: $${"%.2f".format(total)}",
                                    fontSize = 16.sp,
                                    color = col,
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Items: $totalCartItems",
                                    fontSize = 16.sp,
                                    color = col,
                                )
                            }
                        }
                    }
                    Divider(
                        color = Color(0xFF1D0057),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                }
                if (cartItems.isEmpty()) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Your cart is empty.",
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    items(cartItems) { item ->
                        AnimatedCartItemRow(
                            item = item,
                            onRemove = { viewModel.removeFromCart(item.product.id) },
                            onIncrease = { viewModel.increaseQuantity(item.product.id) },
                            onDecrease = { viewModel.decreaseQuantity(item.product.id) }
                        )
                    }

                    item {
                        Button(
                            onClick = { onNavigateCheckout() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF907E36))
                        ) {
                            Text(
                                "Checkout",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
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
            val inactiveColor = Color(0xFF1D0057)
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = col,
                    modifier = Modifier
                    .clickable { onNavigateHome() })
            Box(modifier = Modifier
                .size(40.dp)
                .clickable { onNavigateFavorite() }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = col,
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
                        Text(
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
                modifier = Modifier.size(40.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = inactiveColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(25.dp)
                )


                if (totalCartItems > 0) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(Color.Red, shape = CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (totalCartItems > 99) "99+" else "$totalCartItems",
                            color = Color.White,
                            fontSize = 10.sp,
                            maxLines = 1
                        )
                    }
                }
            }

        }
    }
}


@Composable
fun AnimatedCartItemRow(
    item: CartItems,
    onRemove: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(500)),
        exit = fadeOut(tween(300))
    ) {
        CartItemRow(
            item = item,
            onRemove = onRemove,
            onIncrease = onIncrease,
            onDecrease = onDecrease
        )
    }
}

@Composable
fun CartItemRow(
    item: CartItems,
    onRemove: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    val quantity = item.quantity
    val stock = item.product.quantity.toIntOrNull() ?: 0
    val price = item.product.price.toDoubleOrNull() ?: 0.0
    val subtotal = quantity * price
    val imageResId = getImageResIdByName(item.product.imageResId)
    val hasDiscount = item.product.discountPercentage != null && item.product.discountPercentage > 0
    val discountedPrice = if (hasDiscount) {
        price - (price * (item.product.discountPercentage!! / 100.0))
    } else {
        price
    }

    val offerEndsAt = item.product.offerEnd?.let { timestamp ->
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        sdf.format(Date(timestamp))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(width = 0.6.dp,
            color = Color(0xFF907E36),
            shape = RoundedCornerShape(5.dp)
            ),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .height(130.dp)
                    .weight(0.4f)
                    .clip(RoundedCornerShape(8.dp)),
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
                if (hasDiscount) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .background(Color(0xFFC60314), RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "-${item.product.discountPercentage}%",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                if (offerEndsAt != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 6.dp)
                            .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Ends: $offerEndsAt",
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.product.title,
                    fontSize = 17.sp,
                    style = MaterialTheme.typography.bodyLarge,
                )
                if (hasDiscount) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "$${"%.2f".format(discountedPrice)}",
                            fontSize = 16.sp,
                            color = Color(0xFF907E36),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$${"%.2f".format(subtotal)}",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            textDecoration = TextDecoration.LineThrough,
                            modifier = Modifier.padding(top = 2.dp),
                            maxLines = 1
                        )
                    }
                } else {
                    Text(
                        text = "$${"%.2f".format(subtotal)}",
                        color = Color(0xFF907E36),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BounceQuantity(
                        quantity = quantity,
                        stock = stock,
                        onIncrease = {
                            if (quantity < stock) onIncrease()
                        },
                        onDecrease = onDecrease,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = onRemove,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove",
                            tint = Color(0xFF1D0057),
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                if (quantity >= stock) {
                    Text(
                        text = "Max stock reached",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun BounceQuantity(
    quantity: Int,
    stock: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableStateOf(1f) }

    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(durationMillis = 200),
        label = "bounce"
    )
    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        Text(
            text = "In stock: ${stock - quantity}",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    scale = 1.2f
                    onDecrease()
                },
                modifier = Modifier.scale(animatedScale)
            ) {
                Text("-", fontSize = 23.sp, color = Color(0xFF6F141C))
            }

            Text(
                "$quantity",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 16.sp
            )

            IconButton(
                onClick = {
                    scale = 1.2f
                    onIncrease()
                },
                modifier = Modifier.scale(animatedScale)
            ) {
                Text("+", fontSize = 21.sp, color = Color(0xFF51951E))
            }
        }
    }

    LaunchedEffect(scale) {
        if (scale > 1f) {
            delay(100)
            scale = 1f
        }
    }
}
