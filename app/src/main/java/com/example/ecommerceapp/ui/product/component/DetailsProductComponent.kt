package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Product

@Composable
fun DetailsScreen(product: Product) {

    val customFontFamily = FontFamily(
        Font(R.font.dancingscript)
    )
    val stCol = Color(0xFF338F82)
    val col = Color(0xFF907E36)
    val colM = Color(0xFFE6E6FA)

    Column(modifier = Modifier.fillMaxSize()) {

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
                color = Color(0xFF907E36),
                modifier = Modifier.padding(start = 16.dp)
            )

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Category",
                tint = Color(0xFF907E36),
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 100.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Top
            ) {

                AsyncImage(
                    model = product.imageResId,
                    contentDescription = null,
                    modifier = Modifier
                        .height(240.dp)
                        .width(175.dp)
                        .size(250.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )


                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {

                    Text("${product.title}",
                        fontFamily = customFontFamily,
                        fontSize = 22.sp,
                    )
                    Text("${product.price} $",
                        fontFamily = customFontFamily,
                        fontSize = 18.sp,
                        color = col
                    )
                    Text(
                        "${product.quantity} in stock",
                        style = MaterialTheme.typography.bodyMedium.copy(stCol)
                    )


                    Spacer(modifier = Modifier.height(16.dp))


                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colM,
                            contentColor = col
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "cart"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add to Cart")
                    }

                }
            }

            Text(
                "${product.description}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp, top = 35.dp, end = 16.dp)
            )
        }
    }
}