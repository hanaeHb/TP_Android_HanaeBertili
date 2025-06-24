package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import androidx.compose.foundation.lazy.items

@Composable
fun SocialMediaSection() {
    val socials = listOf(
        Triple("Instagram", R.drawable.instagram, Color(0xFFEECDF2)),
        Triple("Tiktok", R.drawable.tiktok, Color(0xFFD2D0F4)),
        Triple("Youtube", R.drawable.youtube, Color(0xFFF7D1D1)),
        Triple("Facebook", R.drawable.facebook, Color(0xFFE3CDF0))
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(socials) { social ->
            val (name, iconRes, bgColor) = social

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(bgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = name,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = name,
                    fontSize = 14.sp,
                    color = Color(0xFF1D0057)
                )
            }
        }
    }
}
