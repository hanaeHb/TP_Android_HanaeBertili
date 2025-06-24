package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Product
import com.example.ecommerceapp.data.Entities.routineSteps
import com.example.ecommerceapp.ui.product.ProductViewModel

@Composable
fun ProductsByCategoryScreen(
    viewModel: ProductViewModel,
    brand: String,
    category: String,
    products: List<Product>,
    onProductClick: (String) -> Unit,
    onBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateFavorite: () -> Unit,
    onNavigateCart: () -> Unit,
    onNavigateCategory: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val customFontFamily = FontFamily(Font(R.font.dancingscript))

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                .height(50.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material.Text(
                text = "여자_SKIN",
                fontSize = 22.sp,
                fontFamily = customFontFamily,
                color = Color(0xFF907E36),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material.Text(
                text = "$brand >",
                fontSize = 20.sp,
                color = Color(0xFF1D0057),
                modifier = Modifier
                    .clickable { onBack() }
                    .padding(end = 8.dp),
                style = androidx.compose.ui.text.TextStyle(
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                )
            )
            androidx.compose.material.Text(
                text = "$category >",
                fontSize = 25.sp,
                fontFamily = customFontFamily,
                color = Color(0xFF1D0057)
            )
        }

        Divider(
            color = Color(0xFF1D0057),
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(product.id) },
                    viewModel = viewModel
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 75.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "The right skin care routine",
                        fontSize = 25.sp,
                        color = Color(0xFF1D0057),
                    )
                }
                Divider(
                    color = Color(0xFF1D0057),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(routineSteps) { index, step ->
                        RoutineStepCard(step, onClick = {
                            onNavigateCategory()})

                        if (index < routineSteps.lastIndex) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Arrow",
                                tint = Color(0xFF1D0057),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }

        val totalCartItems = state.cartItems.size
        val favoriteCount = state.products.count { it.isFavorite }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val inactiveColor = Color(0xFF1D0057)

            Box(modifier = Modifier.size(40.dp)) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color(0xFF907E36),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { onNavigateHome() }
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onNavigateFavorite() }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color(0xFF907E36),
                    modifier = Modifier.align(Alignment.Center)
                )
                if (favoriteCount > 0) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Red, CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (favoriteCount > 99) "99+" else "$favoriteCount",
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.category),
                contentDescription = "Category",
                tint = inactiveColor,
                modifier = Modifier.size(32.dp)
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onNavigateCart() }
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color(0xFF907E36),
                    modifier = Modifier.align(Alignment.Center)
                )
                if (totalCartItems > 0) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Red, CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$totalCartItems",
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}
