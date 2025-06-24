package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
@Composable
fun PaymentOptionIcon(drawableResId: Int, contentDescription: String) {
    Image(
        painter = painterResource(id = drawableResId),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(48.dp)
            .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
            .padding(8.dp)
    )
}
