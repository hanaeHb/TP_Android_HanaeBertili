package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.CartItems
import com.example.ecommerceapp.data.Entities.Product
import com.example.ecommerceapp.data.Repository.OrderRepository
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.Screen.AppLanguage
import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun UserOrdersScreen(
    email: String,
    viewModel: ProductViewModel,
    languageState: AppLanguage.Instance,
    onNavigateCart: () -> Unit,
    onNavigateFavorite: () -> Unit,
    onNavigateCategory: () -> Unit,
    onNavigateHome: () -> Unit,
    onBack: () -> Unit,
    onNavigateLogin: () -> Unit
) {
    val userOrders = remember(email) { OrderRepository.getOrdersByEmail(email) }

    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    var expandedLang by remember { mutableStateOf(false) }
    val customFontFamily = FontFamily(Font(R.font.dancingscript))

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.background),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu",
                                tint = Color(0xFF907E36)
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {

                            DropdownMenuItem(
                                text = { Text("Se connecter") },
                                onClick = {
                                    expanded = false
                                    onNavigateLogin()
                                }
                            )

                            androidx.compose.material.Divider(
                                modifier = Modifier.padding(vertical = 4.dp)
                            )

                            DropdownMenuItem(
                                enabled = false,
                                text = {
                                    Text(
                                        "Theme",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                },
                                onClick = {}
                            )

                            DropdownMenuItem(
                                text = { Text("Light Theme") },
                                onClick = {
                                    themeState.mode = Mode.Light
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Calme Theme") },
                                onClick = {
                                    themeState.mode = Mode.Calme
                                    expanded = false
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box {
                        IconButton(onClick = { expandedLang = true }) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Language Menu",
                                tint = Color(0xFF907E36)
                            )
                        }
                        DropdownMenu(
                            expanded = expandedLang,
                            onDismissRequest = { expandedLang = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("English") },
                                onClick = {
                                    languageState.onChange(AppLanguage.AppLanguage.EN)
                                    expandedLang = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Français") },
                                onClick = {
                                    languageState.onChange(AppLanguage.AppLanguage.FR)
                                    expandedLang = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("العربية") },
                                onClick = {
                                    languageState.onChange(AppLanguage.AppLanguage.AR)
                                    expandedLang = false
                                }
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.Start
            ) {
                androidx.compose.material.Text(
                    text = languageState.get("Admin page >"),
                    fontSize = 25.sp,
                    modifier = Modifier
                        .clickable { onBack() }
                        .padding(end = 8.dp),
                    fontFamily = customFontFamily,
                    color = Color(0xFF1D0057),
                    style = TextStyle(
                        textDecoration =TextDecoration.Underline
                    )
                )
                androidx.compose.material.Text(
                    text = languageState.get("$email >"),
                    fontSize = 25.sp,
                    fontFamily = customFontFamily,
                    color = Color(0xFF1D0057),

                )
            }
            androidx.compose.material.Divider(
                color = Color(0xFF1D0057),
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                if (userOrders.isEmpty()) {
                    item {
                        Text(
                            languageState.get("No products available."),
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                } else {
                    item {
                        Text(
                            "Customer products",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF907E36),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    items(userOrders) { item ->
                        val product = viewModel.getProductById(item.product.id)
                        if (product != null) {
                            UserOrderItemCard(
                                product = product,
                                quantity = item.quantity,
                                languageState = languageState
                            )
                        } else {
                            Text("Product not found")
                        }
                    }
                }
            }
        }

        val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
        val favoriteCount = viewModel.state.collectAsState().value.products.count { it.isFavorite }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val inactiveColor = Color(0xFF907E36)
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = inactiveColor,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onNavigateHome() }
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onNavigateFavorite() }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color(0xFF907E36),
                    modifier = Modifier.align(Alignment.Center)
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
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onNavigateCart() }
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color(0xFF907E36),
                    modifier = Modifier.align(Alignment.Center)
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
                        Text(
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

@Composable
fun UserOrderItemCard(
    product: Product,
    quantity: Int,
    languageState: AppLanguage.Instance
) {
    val imageResId = getImageResIdByName(product.imageResId)
    val oldPrice = product.discountPercentage?.let {
        val current = product.price.toFloatOrNull() ?: 0f
        val original = current / (1 - it / 100f)
        String.format("%.2f", original)
    }
    val offerCountdown = product.offerEnd?.let { rememberOfferCountdown(it) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = product.title,
                modifier = Modifier.size(95.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    buildAnnotatedString {
                        append(languageState.get("Name:  "))
                        append(product.title)
                    },
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    buildAnnotatedString {
                        append(languageState.get("Category:  "))
                        append(product.category)
                    },
                    fontSize = 12.sp
                )
                Text(
                    buildAnnotatedString {
                        append(languageState.get("Ordered Quantity:  "))
                        append(quantity.toString())
                    },
                    fontSize = 12.sp
                )
                Text(
                    buildAnnotatedString {
                        append(languageState.get("Available Stock:  "))
                        append(product.quantity)
                    },
                    fontSize = 12.sp
                )
                Text(
                    buildAnnotatedString {
                        append("Brand:  ")
                        append(product.brand)
                    },
                    fontSize = 12.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${product.price}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CAF50)
                    )
                    if (oldPrice != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$$oldPrice",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    if (product.discountPercentage != null) {
                        Text(
                            text = "-${product.discountPercentage}%",
                            color = Color.Red,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                if (offerCountdown != null) {
                    Text(
                        text = offerCountdown,
                        fontSize = 11.sp,
                        color = Color(0xFFDAA520),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                androidx.compose.material.Divider(
                    color = Color(0xFF1D0057),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
