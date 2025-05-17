package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

@Composable
fun RatingStars(rating: Int, modifier: Modifier = Modifier, starSize: Dp = 16.dp) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (index < rating) Color.Black else Color.LightGray,
                modifier = Modifier.size(starSize)
            )
        }
    }
}
