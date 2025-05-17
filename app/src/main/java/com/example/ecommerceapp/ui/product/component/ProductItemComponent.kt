package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
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

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    val customFontFamily = FontFamily(Font(R.font.dancingscript))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .width(250.dp)
            .padding(horizontal = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1A892)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                fontFamily = customFontFamily,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "${product.price} $",
                fontSize = 13.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            RatingStars(rating = 5)
        }
    }
}
