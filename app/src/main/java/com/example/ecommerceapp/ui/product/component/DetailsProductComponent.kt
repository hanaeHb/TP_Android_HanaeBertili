package com.example.ecommerceapp.ui.product.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Product
import com.example.ecommerceapp.ui.product.ProductViewModel
@Composable
fun DetailsScreen(
    viewModel: ProductViewModel,
    product: Product,
    onConfirm: () -> Unit,
    onNavigateCart: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateFavorite: () -> Unit
) {
    val imageResId = getImageResIdByName(product.imageResId)
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    val stCol = Color(0xFF338F82)
    val col = Color(0xFF907E36)
    val colM = Color(0xFFE6E6FA)
    val similarProducts = viewModel.state.collectAsState().value.products
        .filter { it.category.equals(product.category, ignoreCase = true) && it.id != product.id }


        Column(modifier = Modifier.fillMaxSize())
        {

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
                    fontFamily = customFontFamily,
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Product Details >",
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
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(240.dp)
                                    .width(175.dp)
                                    .clip(RoundedCornerShape(24.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            ) {
                                Text(product.title, fontSize = 22.sp, color = Color(0xFF1D0057), fontWeight = FontWeight.Bold)
                                Text("$ ${product.price}", fontSize = 18.sp, color = col, fontWeight = FontWeight.Bold)
                                Text("${product.quantity} in stock", style = MaterialTheme.typography.bodyMedium.copy(color = stCol))
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = { onConfirm() },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = colM, contentColor = col)
                                ) {
                                    Icon(Icons.Default.ShoppingCart, contentDescription = "cart")
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Add to Cart")
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
                    }
                }

                if (similarProducts.isNotEmpty()) {
                    item {
                        Text(
                            text = "Similar Products >",
                            fontSize = 25.sp,
                            fontFamily = customFontFamily,
                            color = Color(0xFF1D0057),
                            modifier = Modifier.padding(top = 8.dp, start = 12.dp, end = 12.dp)
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
                                    onClick = { }
                                )
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
                Icon(imageVector = Icons.Default.Person, contentDescription = "Me", tint = col)
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
