package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Product
import com.example.ecommerceapp.ui.product.ProductViewModel

@Composable
fun DetailsScreen(product: Product, onConfirm: () -> Unit) {
    val imageResId = getImageResIdByName(product.imageResId)
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    val stCol = Color(0xFF338F82)
    val col = Color(0xFF907E36)
    val colM = Color(0xFFE6E6FA)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
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

            Column(modifier = Modifier.padding(top = 100.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
                        Text(product.title, fontFamily = customFontFamily, fontSize = 22.sp)
                        Text("${product.price} $", fontFamily = customFontFamily, fontSize = 18.sp, color = col)
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

                Text(
                    product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 35.dp, end = 16.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = col)
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite", tint = col)
            Icon(imageVector = Icons.Default.Person, contentDescription = "Me", tint = col)
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart", tint = col)
        }
    }
}
