package com.example.ecommerceapp.ui.product.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.data.Entities.Product
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.ecommerceapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.Screen.AppLanguage
import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode


@Composable
fun SynScreen(
    viewModel: ProductViewModel,
    brand: String,
    products: List<Product>,
    onBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateFavorite: () -> Unit,
    onNavigateCart: () -> Unit,
    onCategoryClick: (String, String) -> Unit,
    languageState: AppLanguage.Instance,
) {
    val state by viewModel.state.collectAsState()
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    var expandedLang by remember { mutableStateOf(false) }

    val categoriesWithImages = products
        .filter { it.brand == brand }
        .distinctBy { it.category }
        .mapNotNull { product ->
            val category = product.category
            val imageName = product.imageCategory
            if (category.isNotBlank() && imageName.isNotBlank()) category to imageName else null
        }
    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                .height(50.dp)
                .background(MaterialTheme.colorScheme.background),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Theme Menu",
                            tint = Color(0xFF907E36)
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { androidx.compose.material3.Text("Light Theme") },
                            onClick = {
                                themeState.mode = Mode.Light
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { androidx.compose.material3.Text("Calme Theme") },
                            onClick = {
                                themeState.mode = Mode.Calme
                                expanded = false
                            }
                        )

                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box {
                    IconButton(onClick = { expandedLang = true }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Language Menu",
                            tint = Color(0xFF907E36)
                        )
                    }

                    DropdownMenu(
                        expanded = expandedLang,
                        onDismissRequest = { expandedLang = false }
                    ) {
                        DropdownMenuItem(
                            text = { androidx.compose.material3.Text("English") },
                            onClick = {
                                languageState.onChange(AppLanguage.AppLanguage.EN)
                                expandedLang = false
                            }
                        )
                        DropdownMenuItem(
                            text = { androidx.compose.material3.Text("Français") },
                            onClick = {
                                languageState.onChange(AppLanguage.AppLanguage.FR)
                                expandedLang = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("العربية") },
                            onClick = {
                                languageState.onChange(AppLanguage.AppLanguage.AR)
                                expandedLang = false
                            }
                        )

                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = languageState.get("Category >"),
                fontSize = 20.sp,
                color = Color(0xFF1D0057),
                modifier = Modifier
                    .clickable { onBack() }
                    .padding(end = 8.dp),
                style = androidx.compose.ui.text.TextStyle(
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                )
            )
            Text(
                text = "$brand >",
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
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categoriesWithImages) { (category, imageName) ->
                val context = LocalContext.current
                val imageResId = remember(imageName) {
                    context.resources.getIdentifier(imageName, "drawable", context.packageName)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .clickable { onCategoryClick(category, brand) }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (imageResId != 0) {
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = brand,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(0.5.dp, Color(0xFFCCCCCC), RoundedCornerShape(2.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = category,
                        fontSize = 18.sp,
                        color = Color(0xFF1D0057),
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "Go",
                        tint = Color.Gray
                    )
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
