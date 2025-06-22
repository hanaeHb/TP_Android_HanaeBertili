package com.example.ecommerceapp.ui.product.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.CartItems
import com.example.ecommerceapp.ui.product.ProductViewModel
@Composable
fun CartScreen(viewModel: ProductViewModel) {
    val cartItems = viewModel.state.collectAsState().value.cartItems
    val col = Color(0xFF907E36)
    val colM = Color(0xFFE6E6FA)
    val fontFamily = FontFamily(Font(R.font.dancingscript))

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
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
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Category",
                    tint = col,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (cartItems.isEmpty()) {
                Text(
                    "Your cart is empty.",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(cartItems) { item ->
                        Column {
                            CartItemRow(
                                item = item,
                                onRemove = { viewModel.removeFromCart(item.product.id) },
                                onIncrease = { viewModel.increaseQuantity(item.product.id) },
                                onDecrease = { viewModel.decreaseQuantity(item.product.id) }
                            )
                            Divider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val total = cartItems.sumOf {
                    (it.product.price.toDoubleOrNull() ?: 0.0) * it.quantity
                }

                Text(
                    "Total: $${"%.2f".format(total)}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 24.sp,
                    color = col,
                    fontFamily = fontFamily,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp)
                )

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(240.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colM,
                        contentColor = col
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Confirm Order", fontSize = 16.sp)
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = col)
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite", tint = col)
            Icon(imageVector = Icons.Default.Person, contentDescription = "Me", tint = col)
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart", tint = col)
        }
    }
}

@Composable
private fun CartItemRow(
    item: CartItems,
    onRemove: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    val quantity = item.quantity
    val price = item.product.price.toDoubleOrNull() ?: 0.0
    val subtotal = quantity * price
    val imageResId = getImageResIdByName(item.product.imageResId)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8FF)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.product.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 17.sp
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onDecrease,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFE0E0))
                    ) {
                        Text("-", fontSize = 16.sp, color = Color.Red)
                    }

                    Text(
                        "$quantity",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 16.sp
                    )

                    IconButton(
                        onClick = onIncrease,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD0FFD0))
                    ) {
                        Text("+", fontSize = 16.sp, color = Color.Green)
                    }
                }

                Text(
                    text = "$${"%.2f".format(subtotal)}",
                    color = Color(0xFF907E36),
                    fontSize = 15.sp
                )
            }

            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = "Remove", tint = Color.Red)
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
        enter = fadeIn(),
        exit = fadeOut()
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
fun BounceQuantity(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    var scale by remember { mutableStateOf(1f) }

    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(200)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                scale = 1.2f
                onDecrease()
                scale = 1f
            },
            modifier = Modifier
                .size(28.dp)
                .scale(animatedScale)
                .clip(CircleShape)
                .background(Color(0xFFFFE0E0))
        ) {
            Text("-", fontSize = 16.sp, color = Color.Red)
        }

        Text("$quantity", modifier = Modifier.padding(horizontal = 8.dp))

        IconButton(
            onClick = {
                scale = 1.2f
                onIncrease()
                scale = 1f
            },
            modifier = Modifier
                .size(28.dp)
                .scale(animatedScale)
                .clip(CircleShape)
                .background(Color(0xFFD0FFD0))
        ) {
            Text("+", fontSize = 16.sp, color = Color.Green)
        }
    }
}
