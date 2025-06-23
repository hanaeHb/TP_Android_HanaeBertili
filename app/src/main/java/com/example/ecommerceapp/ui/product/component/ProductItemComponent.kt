package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Product
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import com.example.ecommerceapp.ui.product.ProductIntent
import com.example.ecommerceapp.ui.product.ProductViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProductCard(product: Product, onClick: () -> Unit, viewModel: ProductViewModel) {
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    val imageResId = getImageResIdByName(product.imageResId)

    val price = product.price.toDoubleOrNull() ?: 0.0
    val hasDiscount = product.discountPercentage != null && product.discountPercentage > 0
    val discountedPrice = if (hasDiscount) {
        price - (price * (product.discountPercentage!! / 100.0))
    } else {
        price
    }

    val offerRemainingTime = remember(product.offerEnd) { mutableStateOf("") }

    LaunchedEffect(product.offerEnd) {
        while (true) {
            val now = System.currentTimeMillis()
            val diff = (product.offerEnd ?: 0L) - now

            if (diff > 0) {
                val totalSeconds = diff / 1000
                val days = totalSeconds / 86400
                val hours = (totalSeconds % 86400) / 3600
                val minutes = (totalSeconds % 3600) / 60
                val seconds = totalSeconds % 60

                offerRemainingTime.value = if (days > 0) {
                    String.format(" %dd %02dh:%02dmin:%02ds", days, hours, minutes, seconds)
                } else {
                    String.format(" %02dh:%02dmin:%02ds", hours, minutes, seconds)
                }
            } else {
                offerRemainingTime.value = "Offer ended"
            }

            kotlinx.coroutines.delay(1000L)
        }
    }

    Column(
        modifier = Modifier
            .width(160.dp)
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(200.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE6E6FA)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = if (product.isFavorite) Color(0xFFC60314) else Color.Gray,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(14.dp)
                        .size(24.dp)
                        .clickable {
                            viewModel.handleIntent(ProductIntent.ToggleFavorite(product.id))
                        }
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
                if (hasDiscount && product.offerEnd != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 6.dp)
                            .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AccessTime,
                                contentDescription = "Time Left",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(17.dp)
                                    .padding(end = 3.dp)
                            )
                            Text(
                                text = offerRemainingTime.value,
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color(0xFF1D0057)
        )
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

        Spacer(modifier = Modifier.height(4.dp))

        val safeRating = (product.rating ?: 0f).toInt().coerceIn(0, 5)
        RatingStars(rating = safeRating)
    }
}
