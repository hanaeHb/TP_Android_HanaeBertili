package com.example.ecommerceapp.ui.product.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.ecommerceapp.data.Entities.Product
import com.example.ecommerceapp.data.Entities.routineSteps
import com.example.ecommerceapp.ui.product.ProductIntent
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.Screen.AppLanguage
import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailsScreen(
    viewModel: ProductViewModel,
    product: Product,
    onConfirm: () -> Unit,
    onNavigateCart: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateFavorite: () -> Unit,
    onNavigateToProduct: (Product) -> Unit,
    onNavigateCategory: () -> Unit,
    languageState: AppLanguage.Instance,
    onNavigateLogin: () -> Unit
) {
    val imageResId = getImageResIdByName(product.imageResId)
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    val stCol = Color(0xFF338F82)
    val col = Color(0xFF907E36)
    val colM = Color(0xFFE6E6FA)
    val similarProducts = viewModel.state.collectAsState().value.products
        .filter { it.category.equals(product.category, ignoreCase = true) && it.id != product.id }
    val brandProducts = viewModel.state.collectAsState().value.products
        .filter { it.brand.equals(product.brand, ignoreCase = true) && it.id != product.id }
    val price = product.price.toDoubleOrNull() ?: 0.0
    val hasDiscount = product.discountPercentage != null && product.discountPercentage > 0
    val discountedPrice = if (hasDiscount) {
        price - (price * (product.discountPercentage!! / 100.0))
    } else {
        price
    }
    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    val offerEndsAt = product.offerEnd?.let { timestamp ->
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        sdf.format(Date(timestamp))
    }
    var userRating by remember { mutableStateOf(product.rating ?: 0f) }
    var expandedLang by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),)
        {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "여자_SKIN",
                    fontSize = 22.sp,
                    fontFamily = customFontFamily,
                    color = col,
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
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = languageState.get("Product Details >"),
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
                    .padding(bottom = 16.dp)
            ) {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(240.dp)
                                    .width(175.dp)
                                    .clip(RoundedCornerShape(24.dp))
                            ) {
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                if (hasDiscount) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .padding(13.dp)
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
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            ) {
                                Text(product.title, fontSize = 22.sp, color = Color(0xFF1D0057), fontWeight = FontWeight.Bold)
                                if (hasDiscount) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(
                                            text = "$${String.format("%.2f", discountedPrice)}",
                                            fontSize = 14.sp,
                                            color = Color(0xFF907E36),
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "$${product.price}",
                                            fontSize = 12.sp,
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
                                        fontSize = 14.sp,
                                        color = Color(0xFF907E36),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text("${product.quantity} in stock", style = MaterialTheme.typography.bodyMedium.copy(color = stCol))
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = { onConfirm() },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                ) {
                                    Icon(Icons.Default.ShoppingCart, contentDescription = "cart")
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(languageState.get("Add to Cart"))
                                }
                            }
                        }

                        Card(
                            modifier = Modifier
                                .padding(vertical = 24.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F2F2)),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Text(
                                text = product.description,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        Text(
                            text =languageState.get( "Your rating >"),
                            fontSize = 18.sp,
                            color = Color(0xFF1D0057),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp, start = 8.dp)
                        )

                        StarRating(
                            rating = userRating,
                            onRatingChanged = { newRating ->
                                userRating = newRating
                                viewModel.handleIntent(ProductIntent.RateProduct(product.id, newRating))
                            }
                        )
                        Divider(
                            color = Color(0xFF1D0057),
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }

                if (similarProducts.isNotEmpty()) {
                    item {
                        Text(
                            text = languageState.get("You Might Like >"),
                            fontSize = 25.sp,
                            fontFamily = customFontFamily,
                            color = Color(0xFF1D0057),
                            modifier = Modifier.padding(top = 40.dp, start = 12.dp, end = 12.dp)
                        )
                        Divider(
                            color = Color(0xFF1D0057),
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp)
                        ) {
                            items(similarProducts) { similarProduct ->
                                SimilarProductCard(
                                    product = similarProduct,
                                    onClick = { onNavigateToProduct(similarProduct) }
                                )
                            }
                        }
                    }
                }

                if (brandProducts.isNotEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 75.dp, start = 12.dp, end = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${product.brand}",
                                fontSize = 25.sp,
                                color = Color(0xFF1D0057)
                            )
                        }
                        Divider(
                            color = Color(0xFF1D0057),
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp)
                        ) {
                            items(brandProducts) { brandProduct ->
                                ProductCard(
                                    product = brandProduct,
                                    onClick = { onNavigateToProduct(brandProduct) },
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 75.dp, start = 12.dp, end = 12.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = languageState.get("The right skin care routine"),
                            fontSize = 20.sp,
                            color = Color(0xFF1D0057),
                        )
                    }
                    Divider(
                        color = Color(0xFF1D0057),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(routineSteps) { index, step ->
                            RoutineStepCard(step, onClick = {
                                onNavigateCategory()})

                            if (index < routineSteps.lastIndex) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Arrow",
                                    tint = Color(0xFF1D0057),
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                }
            }

            val favoriteCount = viewModel.state.collectAsState().value.products.count { it.isFavorite }
            val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(77.dp)
                    .padding(bottom = 45.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onNavigateCart() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = col,
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
