package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.data.Entities.Product
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SimilarProductCard(product: Product, onClick: () -> Unit) {
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
            .width(290.dp)
            .wrapContentHeight()
            .padding(end = 12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF907E36)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F2F2))
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
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
                            .padding(4.dp)
                            .background(Color(0xFFC60314), RoundedCornerShape(8.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "-${product.discountPercentage}%",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
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

                if (offerEndsAt != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Ends: $offerEndsAt",
                            fontSize = 9.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(3.dp)
                                .background(Color(0xAA000000), RoundedCornerShape(4.dp))
                                .padding(horizontal = 3.dp, vertical = 1.5.dp)
                        )
                    }
                }
            }
        }
    }
}
