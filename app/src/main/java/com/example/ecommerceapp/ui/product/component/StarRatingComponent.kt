package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun StarRating(
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            val filled = i <= rating
            Icon(
                imageVector = if (filled) Icons.Filled.Star else Icons.Default.StarBorder,
                contentDescription = "Star $i",
                tint = Color(0xFFFFD700),
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        onRatingChanged(i.toFloat())
                    }
            )
        }
    }
}
