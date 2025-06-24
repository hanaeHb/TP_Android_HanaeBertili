package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip

@Composable
fun SelectableImage(imageResId: Int, label: String) {
    var selected by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (selected) Color(0xFF907E36).copy(alpha = 0.2f) else Color.Transparent)
                .clickable { selected = !selected },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = label,
                modifier = Modifier.size(120.dp)
            )
        }
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}
