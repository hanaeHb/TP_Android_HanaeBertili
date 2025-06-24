package com.example.ecommerceapp.nav



import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box


import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecommerceapp.ui.product.component.DetailsScreen
import com.example.ecommerceapp.ui.product.Screen.HomeScreen
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.component.CartScreen
import kotlinx.coroutines.delay
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.ui.product.component.CategoryScreen
import com.example.ecommerceapp.ui.product.component.CheckoutScreen
import com.example.ecommerceapp.ui.product.component.FavoriteScreen
import com.example.ecommerceapp.ui.product.component.ProductsByCategoryScreen
import com.example.ecommerceapp.ui.product.component.SynScreen

object Routes {
    const val  Greating = "Greating"
    const val  Home = "Home"
    const val  ProductDetails = "ProductDetails"
    const val  Cart = "Cart"
    const val  Favorite = "Favorite"
    const val  Category = "Category"
    const val  SynScreen = "syn_screen"
    const val  ProductsByCategory = "products_by_category"
    const val  Checkout = "Checkout"

}
@Composable
fun AppNav(viewModel: ProductViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.Greating) {
        composable(Routes.Greating) {
            GreetingScreen(onNavigateToHome = {
                navController.navigate(Routes.Home) {
                    popUpTo(Routes.Greating) { inclusive = true }
                }
            })
        }

        composable(Routes.Home) {
            HomeScreen(viewModel, onProductClick = { productId ->
                navController.navigate("${Routes.ProductDetails}/$productId")
                },
                    onNavigateCart = {
                    navController.navigate(Routes.Cart)
                },
                    onNavigateFavorite = {
                    navController.navigate(Routes.Favorite)
                },
                    onNavigateCategory = {
                    navController.navigate(Routes.Category)
                }
            )
        }

        composable(
            "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val product = viewModel.getProductById(productId)

            if (product != null) {
                DetailsScreen(viewModel = viewModel,
                    product = product,
                    onConfirm = {
                        viewModel.addToCart(product)
                        navController.navigate(Routes.Cart)
                    },
                    onNavigateCart = {
                        navController.navigate(Routes.Cart)
                    },
                    onNavigateHome = {
                        navController.navigate(Routes.Home)
                    },
                    onNavigateFavorite = {
                        navController.navigate(Routes.Favorite)
                    },
                    onNavigateToProduct = { selectedProduct ->
                        navController.navigate("${Routes.ProductDetails}/${selectedProduct.id}")
                    },
                    onNavigateCategory = {
                        navController.navigate(Routes.Category)
                    }
                )
            } else {
                Text("Product not found", color = Color.Red, modifier = Modifier.padding(16.dp))
            }
        }
        composable(Routes.Cart) {
            CartScreen(viewModel = viewModel,
                onNavigateHome = {
                    navController.navigate(Routes.Home)
                },
                onNavigateFavorite = {
                    navController.navigate(Routes.Favorite)
                },
                onNavigateCategory = {
                    navController.navigate(Routes.Category)
                },
                onNavigateCheckout = {
                    navController.navigate(Routes.Checkout)
                }
            )
        }
        composable(Routes.Favorite) {
                FavoriteScreen(
                    viewModel = viewModel,
                    onNavigateCart = {
                        navController.navigate(Routes.Cart)
                    },
                    onNavigateHome = {
                        navController.navigate(Routes.Home)
                    },
                    onClick = { productId ->
                        navController.navigate("${Routes.ProductDetails}/$productId")
                    },
                    onNavigateCategory = {
                        navController.navigate(Routes.Category)
                    }
                )
        }

        composable(Routes.Category) {
            CategoryScreen(
                viewModel = viewModel,
                onNavigateHome = {
                    navController.navigate(Routes.Home)
                },
                onNavigateFavorite = {
                    navController.navigate(Routes.Favorite)
                },
                onNavigateCart = {
                    navController.navigate(Routes.Cart)
                },
                onBrandClick = { brand ->
                    navController.navigate("${Routes.SynScreen}/${Uri.encode(brand)}")
                }
            )
        }
        composable(
            route = "${Routes.SynScreen}/{brand}",
            arguments = listOf(navArgument("brand") { type = NavType.StringType })
        ) { backStackEntry ->
            val brand = backStackEntry.arguments?.getString("brand") ?: ""

            val state by viewModel.state.collectAsState()
            SynScreen(
                viewModel = viewModel,
                brand = brand,
                products = state.products,
                onBack = { navController.popBackStack() }
                ,
                onNavigateHome = {
                    navController.navigate(Routes.Home)
                },
                onNavigateFavorite = {
                    navController.navigate(Routes.Favorite)
                },
                onNavigateCart = {
                    navController.navigate(Routes.Cart)
                },
                onCategoryClick = { category, brand ->
                    navController.navigate("${Routes.ProductsByCategory}/${Uri.encode(brand)}/${Uri.encode(category)}")
                }
            )
        }

        composable(
            route = "${Routes.ProductsByCategory}/{brand}/{category}",
            arguments = listOf(
                navArgument("brand") { type = NavType.StringType },
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val brand = backStackEntry.arguments?.getString("brand") ?: ""
            val category = backStackEntry.arguments?.getString("category") ?: ""

            val state by viewModel.state.collectAsState()

            val filteredProducts = state.products.filter {
                it.brand == brand && it.category == category
            }
            ProductsByCategoryScreen(
                viewModel = viewModel,
                brand = brand,
                category = category,
                products = filteredProducts,
                onProductClick = { productId ->
                    navController.navigate("${Routes.ProductDetails}/$productId")
                },
                onBack = {
                    navController.popBackStack()
                },
                onNavigateCart = {
                    navController.navigate(Routes.Cart)
                },
                onNavigateHome = {
                    navController.navigate(Routes.Home)
                },
                onNavigateFavorite = {
                    navController.navigate(Routes.Favorite)
                },
                onNavigateCategory = {
                    navController.navigate(Routes.Category)
                }
            )
        }


        composable(Routes.Checkout) {
            CheckoutScreen(
                viewModel = viewModel,
                onNavigateCart = {
                    navController.navigate(Routes.Cart)
                },
                onNavigateCategory = {
                    navController.navigate(Routes.Category)
                },
                onNavigateFavorite = {
                    navController.navigate(Routes.Favorite)
                },
                onNavigateHome = {
                    navController.navigate(Routes.Home)
                }
            )
        }

    }
}

@Composable
fun GreetingScreen(onNavigateToHome: () -> Unit) {
    val alphaAnim = remember { Animatable(0f) }
    val progressAnim = remember { Animatable(0f) }
    val col = Color(0xFFBE9FEF)

    val customFontFamily = FontFamily(Font(R.font.dancingscript))

    LaunchedEffect(Unit) {
        alphaAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(2000)
        )
        progressAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(5000)
        )
        delay(5000)
        onNavigateToHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(col)
    ) {

        Image(
            painter = painterResource(id = R.drawable.fav8),
            contentDescription = "Welcome Image",
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim.value),
            
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            LoadingText(progressAnim)
        }
    }
}

@Composable
fun LoadingText(progressAnim: Animatable<Float, *>) {
    Text(
        text = ".....",
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .alpha(progressAnim.value),
        color = Color(0xFF5C5122),
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}
