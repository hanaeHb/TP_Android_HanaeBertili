package com.example.ecommerceapp.nav

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecommerceapp.ui.product.component.DetailsScreen
import com.example.ecommerceapp.ui.product.Screen.HomeScreen
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.component.CartScreen
import com.example.ecommerceapp.ui.product.component.CategoryScreen
import com.example.ecommerceapp.ui.product.component.CheckoutScreen
import com.example.ecommerceapp.ui.product.component.FavoriteScreen
import com.example.ecommerceapp.ui.product.component.LoginScreen
import com.example.ecommerceapp.ui.product.component.OrderTrackingScreen
import com.example.ecommerceapp.ui.product.component.ProductsByCategoryScreen
import com.example.ecommerceapp.ui.product.component.RegisterScreen
import com.example.ecommerceapp.ui.product.component.SynScreen
import com.example.ecommerceapp.ui.theme.ThemeState

object Routes {
    const val  Register = "Register"
    const val  Login = "Login"
    const val  Home = "Home"
    const val  ProductDetails = "ProductDetails"
    const val  Cart = "Cart"
    const val  Favorite = "Favorite"
    const val  Category = "Category"
    const val  SynScreen = "syn_screen"
    const val  ProductsByCategory = "products_by_category"
    const val  Checkout = "Checkout"
    const val  Tracking = "Tracking"

}
@Composable
fun AppNav(viewModel: ProductViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.Login) {
        composable(Routes.Login) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Routes.Home) },
                onNavigateToRegister = { navController.navigate(Routes.Register) }
            )
        }
        composable(Routes.Register) {
            RegisterScreen(
                onRegisterSuccess = { navController.popBackStack() }
            )
        }

        composable(Routes.Home) {
            HomeScreen(viewModel,
                onProductClick = { productId ->
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
                },
                onNavigateTrack = {
                    navController.navigate(Routes.Tracking)
                }
            )
        }
        composable(Routes.Tracking){
            OrderTrackingScreen(viewModel = viewModel,
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
