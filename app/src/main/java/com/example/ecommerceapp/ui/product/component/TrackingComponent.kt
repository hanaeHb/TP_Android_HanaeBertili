package com.example.ecommerceapp.ui.product.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.*
import androidx.compose.ui.draw.scale
import com.example.ecommerceapp.ui.product.Screen.AppLanguage
import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode
import kotlinx.coroutines.delay

@Composable
fun OrderTrackingScreen(viewModel: ProductViewModel, onNavigateCart: () -> Unit, onNavigateFavorite: () -> Unit,
                        onNavigateCategory: () -> Unit, onNavigateHome: () -> Unit, languageState: AppLanguage.Instance,onNavigateLogin: () -> Unit) {
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
    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    var expandedLang by remember { mutableStateOf(false) }

    val stages = listOf(
        Pair(Icons.Filled.CheckCircle, "Order Placed"),
        Pair(Icons.Filled.Build, "Preparing your order"),
        Pair(Icons.Filled.LocalShipping, "Shipped"),
        Pair(Icons.Filled.TwoWheeler, "Out for Delivery")
    )

    val totalStages = stages.size
    val totalDurationMs = totalStages * 10_000L
    val stepDelayMs = 100L
    val steps = (totalDurationMs / stepDelayMs).toInt()
    val increment = 1f / steps
    val totalPrice = cartItems.sumOf {
        val price = it.product.price.toDoubleOrNull() ?: 0.0
        val discount = it.product.discountPercentage ?: 0
        val discounted = price * (1 - discount / 100.0)
        discounted * it.quantity
    }
    var progress by remember { mutableStateOf(0f) }
    var finished by remember { mutableStateOf(false) }

    LaunchedEffect(finished) {
        if (!finished) {
            progress = 0f
            repeat(steps) {
                delay(stepDelayMs)
                progress += increment
            }
            progress = 1f
            finished = true
        }
    }

    val currentStageIndex = (progress * totalStages).toInt().coerceIn(0, totalStages - 1)
    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),) {


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
                            text = { androidx.compose.material3.Text("Se connecter") },
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
                                androidx.compose.material3.Text(
                                    "Theme",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray
                                )
                            },
                            onClick = {}
                        )

                        DropdownMenuItem(
                            text = { androidx.compose.material3.Text("Light Theme") },
                            onClick = {
                                themeState.mode = Mode.Light
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { androidx.compose.material3.Text("Calme Theme") },
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
                        androidx.compose.material3.Icon(
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
                            text = { androidx.compose.material3.Text("English") },
                            onClick = {
                                languageState.onChange(AppLanguage.AppLanguage.EN)
                                expandedLang = false
                            }
                        )
                        DropdownMenuItem(
                            text = { androidx.compose.material3.Text("Français") },
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
            Text(
                text = languageState.get("Tracking >"),
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
                            languageState.get("Your Informations:"),
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
                                append(languageState.get("Email: "))
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append(client.email)
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append(languageState.get("Name: "))
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append("${client.firstName} ${client.lastName}")
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append(languageState.get("Country: "))
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append(client.country)
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append(languageState.get("Address: "))
                            }
                            withStyle(SpanStyle(color = valueStyle.color, fontSize = valueStyle.fontSize)) {
                                append(client.address)
                            }
                        })

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = labelStyle.color, fontSize = labelStyle.fontSize)) {
                                append(languageState.get("Postal Code: "))
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
            item {
                Text(languageState.get("Order Tracking"),  modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!finished) {
                        Icon(
                            imageVector = stages[currentStageIndex].first,
                            contentDescription = stages[currentStageIndex].second,
                            tint = Color(0xFF1D0057),
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        androidx.compose.material3.Text(
                            text = stages[currentStageIndex].second,
                            fontSize = 19.sp,
                            color = Color(0xFF1D0057)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        LinearProgressIndicator(
                            progress = progress.coerceIn(0f, 1f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = Color(0xFF907E36),
                            trackColor = Color(0xFFD7CDAF)
                        )
                    } else {
                        val infiniteTransition = rememberInfiniteTransition()
                        val scale by infiniteTransition.animateFloat(
                            initialValue = 1f,
                            targetValue = 1.2f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(600, easing = LinearEasing),
                                repeatMode = RepeatMode.Reverse
                            )
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "\uD83E\uDD73",
                                fontSize = 48.sp,
                                modifier = Modifier.scale(scale)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            androidx.compose.material3.Text(
                                text = languageState.get("Your order will arrive within 48 hours"),
                                fontSize = 18.sp,
                                color = Color(0xFF907E36)
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
                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F2F2)),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            languageState.get("Your Package and your gift"),
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                        if (selectedIndex != -1) {
                            val (giftRes, packageRes, colorName) = options[selectedIndex]
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                                    .padding(top = 10.dp),
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
                            }
                        }

                        Text(
                            languageState.get("Your products"),
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                        cartItems.forEach { item ->
                            val price = item.product.price.toDoubleOrNull() ?: 0.0
                            val discount = item.product.discountPercentage ?: 0
                            val discountedPrice = price * (1 - discount / 100.0)
                            val imageResId = getImageResIdByName(item.product.imageResId)

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                            ) {
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
                                        Text(item.product.title, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "${item.quantity} x $${"%.2f".format(discountedPrice)}",
                                            fontSize = 14.sp,
                                            color = Color(0xFF1D0057)
                                        )
                                    }
                                }
                            }
                            Divider(
                                color = Color(0xFF1D0057),
                                thickness = 0.5.dp,
                                modifier = Modifier.padding(vertical = 6.dp)
                            )
                        }

                        Text(
                            "$totalQuantity items",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color(0xFF1D0057)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Price total of products: $${"%.2f".format(totalPrice)}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color(0xFF1D0057)
                        )

                        val deliveryFee = 5.50
                        val totalWithDelivery = totalPrice + deliveryFee

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
                                    languageState.get("Delivery: $5.50"),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color(0xFF1D0057)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    "Total: $${"%.2f".format(totalWithDelivery)}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color(0xFF1D0057)
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 75.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    androidx.compose.material3.Text(
                        text = languageState.get("MORE OF OUR SOCIALS"),
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