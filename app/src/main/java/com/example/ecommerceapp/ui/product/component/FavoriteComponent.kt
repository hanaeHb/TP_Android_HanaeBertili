package com.example.ecommerceapp.ui.product.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.product.ProductIntent
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.component.ProductCard
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.example.ecommerceapp.data.Entities.routineSteps
import com.example.ecommerceapp.ui.product.Screen.AppLanguage
import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FavoriteScreen(viewModel: ProductViewModel, onNavigateCart: () -> Unit, onNavigateHome: () ->  Unit, onClick: (String) -> Unit,
                   onNavigateCategory: () -> Unit,  languageState: AppLanguage.Instance,) {
    val state by viewModel.state.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    LaunchedEffect(Unit) {
        if (state.products.isEmpty()) {
            viewModel.handleIntent(ProductIntent.LoadProducts)
        }
    }

    var expandedLang by remember { mutableStateOf(false) }
    val favoriteProducts = state.products.filter { it.isFavorite }
    val fontFamily = FontFamily(Font(R.font.dancingscript))

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background))
    {


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
                            contentDescription = "Theme Menu",
                            tint = Color(0xFF907E36)
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
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
            Text(
                text = languageState.get("My Fvorite >"),
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(top = 15.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(favoriteProducts) { product ->
                val imageResId = getImageResIdByName(product.imageResId)
                val price = product.price.toDoubleOrNull() ?: 0.0
                val hasDiscount = product.discountPercentage != null && product.discountPercentage > 0
                val discountedPrice = if (hasDiscount) {
                    price - (price * (product.discountPercentage!! / 100.0))
                } else {
                    price
                }

                val offerEndsAt = product.offerEnd?.let { timestamp ->
                    val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
                    sdf.format(Date(timestamp))
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable { onClick(product.id) },
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .size(155.dp)
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            if (hasDiscount) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(10.dp)
                                        .background(Color(0xFFC60314), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "-${product.discountPercentage}%",
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
                                        .padding(horizontal = 12.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Ends: $offerEndsAt",
                                        color = Color.White,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }

                        Text(
                            text = product.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color(0xFF1D0057)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (hasDiscount) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = "$${String.format("%.2f", discountedPrice)}",
                                        fontSize = 16.sp,
                                        color = Color(0xFF907E36),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "$${product.price}",
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
                                    text = "$${product.price}",
                                    fontSize = 16.sp,
                                    color = Color(0xFF907E36),
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            IconButton(onClick = {
                                viewModel.toggleFavorite(product.id)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove from favorite",
                                    tint = Color(0xFF1D0057)
                                )
                            }
                        }
                    }
                }
            }
        }
        val inactiveColor = Color(0xFF1D0057)
        val favoriteCount = state.products.count { it.isFavorite }
        val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color(0xFF907E36),
                    modifier = Modifier
                        .clickable { onNavigateHome() }
                )
            Box(modifier = Modifier.size(40.dp)) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = inactiveColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(25.dp)
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
