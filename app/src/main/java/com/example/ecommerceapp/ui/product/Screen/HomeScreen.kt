package com.example.ecommerceapp.ui.product.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.ui.product.ProductIntent
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.component.ProductCard
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale

@Composable
fun HomeScreen(viewModel: ProductViewModel = viewModel(), onProductClick: (String) -> Unit) {
    val state by viewModel.state.collectAsState()

    val customFontFamily = FontFamily(
        Font(R.font.dancingscript)
    )

    LaunchedEffect(viewModel) {
        viewModel.handleIntent(ProductIntent.LoadProducts)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
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

        if (state.isLoading) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Loading products...",
                    color = Color(0xFF907E36),
                    )
            }
        } else if (state.error != null) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Error: ${state.error}")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 64.dp)
            ) {
                item(span = { GridItemSpan(2) }) {
                    Image(
                        painter = painterResource(id = R.drawable.bestskincare),
                        contentDescription = "Intro",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(195.dp)
                            .padding(top = 0.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                item(span = { GridItemSpan(2) }) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        listOf(
                            Pair(R.drawable.sunscreen1, "Sunscreen"),
                            Pair(R.drawable.serumm, "Serum"),
                            Pair(R.drawable.mask, "Mask"),
                            Pair(R.drawable.cream, "Hydrate Cream"),
                            Pair(R.drawable.toner2, "Toner"),
                        ).forEach { (iconRes, label) ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(top = 80.dp)
                            ) {
                                ZoomableImage(iconRes = iconRes, label = label)
                                Text(
                                    text = label,
                                    color = Color(0xFF907E36),
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }


                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Products for you my lady",
                        color = Color(0xFF907E36),
                        fontSize = 32.sp,
                        fontFamily = customFontFamily,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 80.dp, bottom = 25.dp)
                    )
                }

                items(state.products) { product ->
                    ProductCard(product = product, onClick = { onProductClick(product.id) })
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = Color(0xFF907E36),)
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color(0xFF907E36),)
            Icon(imageVector = Icons.Default.Person, contentDescription = "Me", tint = Color(0xFF907E36),)
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color(0xFF907E36),)
        }
    }
}

@Composable
fun ZoomableImage(iconRes: Int, label: String) {
    var clicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(if (clicked) 1.2f else 1f)

    Image(
        painter = painterResource(id = iconRes),
        contentDescription = label,
        modifier = Modifier
            .size(78.dp)
            .clip(CircleShape)
            .background(Color(0xFFECECEC), shape = CircleShape)
            .border(2.8.dp, Color(0xFFE6E6FA), CircleShape)
            .padding(1.dp)
            .scale(scale)
            .clickable { clicked = !clicked },
        contentScale = ContentScale.Crop
    )
}
