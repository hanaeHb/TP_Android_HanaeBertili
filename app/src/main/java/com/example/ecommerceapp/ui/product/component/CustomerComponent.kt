package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.Screen.AppLanguage
import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode

@Composable
fun CustomersScreen(
    productId: String,
    viewModel: ProductViewModel,
    languageState: AppLanguage.Instance,
    onNavigateCart: () -> Unit,
    onNavigateFavorite: () -> Unit,
    onNavigateCategory: () -> Unit,
    onNavigateHome: () -> Unit,
    onBack: () -> Unit,
    productTitle: String,
    showUserOrders: (String) -> Unit,
    onNavigateLogin: () -> Unit
) {
    val clients = remember(productId) {
        viewModel.getClientsByProduct(productId)
    }

    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    var expandedLang by remember { mutableStateOf(false) }
    val customFontFamily = FontFamily(Font(R.font.dancingscript))

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {

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
                                contentDescription = "Menu",
                                tint = Color(0xFF907E36)
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {

                            DropdownMenuItem(
                                text = { Text("Se connecter") },
                                onClick = {
                                    expanded = false
                                    onNavigateLogin()
                                }
                            )

                            androidx.compose.material.Divider(
                                modifier = Modifier.padding(vertical = 4.dp)
                            )

                            DropdownMenuItem(
                                enabled = false,
                                text = {
                                    Text(
                                        "Theme",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                },
                                onClick = {}
                            )

                            DropdownMenuItem(
                                text = { Text("Light Theme") },
                                onClick = {
                                    themeState.mode = Mode.Light
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Calme Theme") },
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
                            Icon(
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
                                text = { Text("English") },
                                onClick = {
                                    languageState.onChange(AppLanguage.AppLanguage.EN)
                                    expandedLang = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Français") },
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
                horizontalArrangement = Arrangement.Start
            ) {
                androidx.compose.material.Text(
                    text = languageState.get("Admin page >"),
                    fontSize = 25.sp,
                    modifier = Modifier
                        .clickable { onBack() }
                        .padding(end = 8.dp),
                    fontFamily = customFontFamily,
                    color = Color(0xFF1D0057),
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
                androidx.compose.material.Text(
                    text = "$productTitle",
                    fontSize = 25.sp,
                    fontFamily = customFontFamily,
                    color = Color(0xFF1D0057),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    )
            }
            androidx.compose.material.Divider(
                color = Color(0xFF1D0057),
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    Text(
                        "Clients who bought this product:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF907E36),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                if (clients.isEmpty()) {
                    item {
                        Text("No clients bought this product yet.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            textAlign = TextAlign.Center)
                    }
                } else {
                    items(clients) { client ->
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Text(
                                "Name: ${client.firstName} ${client.lastName}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Email: ${client.email}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Phone: ${client.phone}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { showUserOrders(client.email) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D0057)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Products",
                                    tint = Color.White,
                                    modifier = Modifier.size(25.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    languageState.get("Products"),
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            androidx.compose.material.Divider(
                                color = Color(0xFF1D0057),
                                thickness = 0.5.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }

        val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
        val favoriteCount = viewModel.state.collectAsState().value.products.count { it.isFavorite }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val inactiveColor = Color(0xFF907E36)
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = inactiveColor,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onNavigateHome() }
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onNavigateFavorite() }) {
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
                tint = Color(0xFF907E36),
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onNavigateCategory() }
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
