package com.example.ecommerceapp.nav



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

object Routes {
    const val  Greating = "Greating"
    const val  Home = "Home"
    const val  ProductDetails = "ProductDetails"
    const val Cart = "Cart"
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
            })
        }

        composable(
            "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val product = viewModel.getProductById(productId)

            if (product != null) {
                DetailsScreen(
                    product = product,
                    onConfirm = {
                        viewModel.addToCart(product)
                        navController.navigate(Routes.Cart)
                    }
                )
            } else {
                Text("Product not found", color = Color.Red, modifier = Modifier.padding(16.dp))
            }
        }
        composable(Routes.Cart) {
            CartScreen(viewModel = viewModel)
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


/*
@Composable
fun HomeScreen(onProductClick: (String) -> Unit) {
    val products = listOf(
        Product("P1", "Dr.Althea – 345 Relief Cream", "Resveratrol 345NA – Intensive Repair Cream is a regenerating ointment gel cream rich in nutrients. The product strengthens the cell regeneration function in our skin for firmer skin while reducing the appearance of fine lines and wrinkles."
                ,  R.drawable.dralthea, 990.99, "Hydrate Cream", 12),
        Product("P2", "iUNIK – Centella Calming Gel Cream", "iUNIK Centella Calming Gel Cream is formulated with Centella Asiatica Leaf Water and Tea Tree Leaf Water to soothe and hydrate the skin."
            , R.drawable.centella, 399.99, "Hydrate Cream", 5),
        Product("P3", "Beauty of joseon-Ground", "Beauty of Joseon Ground Rice and Honey Glow Serum is a serum enriched with ground rice and honey, designed to enhance skin radiance and hydration."
            , R.drawable.joseon, 595.90, "Serum", 20),
        Product("P4", "Medicube PDRN Pink One Day Serum Set", "Packed with 99% salmon PDRN and collagen, this potent anti-aging ampoule restore skin elasticity and promote skin cell regeneration."
            , R.drawable.pdrn, 1500.00, "Serum", 10),
        Product("P5", "Anua – Peach Niacin UV Tone Up", "This tone up sunscreen is fitted with SPF50+ PA++++ broad-spectrum sun protection. It’s formulated with peach extract, alongside Vitamin B12 and 20,000ppm of niacinamide to brighten skin and fade blemishes while boosting skin hydration."
            , R.drawable.anua, 2600.00, "Sunscreen", 8),
        Product("P6", "Beauty of Joseon – Sunscreen", "This organic Beauty of Joseon sunscreen is fitted with SPF 50+ PA++++ to fend off UVA and UVB rays. Lightweight formula comes in a quick-absorbent texture and doesn’t leave a white cast. Additional ingredients of rice extract and grain-derived probiotics keep skin supple and hydrated."
            , R.drawable.relief, 500.90, "Sunscreen", 33),
        Product("P7", "SKIN 1004 Madagascar Centella", "Blended with pink Himalayan salt and centella asiatica extract, this do-it-all clay stick mask soothes sensitive skin and purifies pores for clear and healthy skin. It features red bean powder to buff away dead skin cells for refined skin."
            , R.drawable.centellasirom, 2000.00, " Mask", 29),
        Product("P8", "Mary & May Calendula Peptide Ageless", "In order to reduce inflammation, heal damaged skin, and soothe irritations, this sleeping mask contains 2,200ppm of calendula extract, peptides, and cica extract. A boost of hydration is provided by an additional hyaluronic acid component."
            , R.drawable.marymay, 3600.00, "Serum", 0),
    )
    val customFontFamily = FontFamily(
        Font(R.font.dancingscript)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(50.dp)
                .background(Color(0xFFF1A892)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "여자_SKIN",
                fontSize = 22.sp,
                fontFamily = customFontFamily,
                modifier = Modifier.padding(start = 16.dp)
            )

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Category",
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 64.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Image(
                    painter = painterResource(id = R.drawable.introo),
                    contentDescription = "Intro",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Products for you my lady",
                    fontSize = 32.sp,
                    fontFamily = customFontFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 150.dp, bottom = 25.dp)
                )
            }

            items(products) { product ->
                ProductCard(product = product, onClick = { onProductClick(product.id) })
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
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            Icon(imageVector = Icons.Default.Person, contentDescription = "Me")
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Panier")
        }
    }
}*/
/*
@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    val customFontFamily = FontFamily(
        Font(R.font.dancingscript)
    )
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

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 4.dp)
            ) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}*/

/*
@Composable
fun DetailsScreen(productId: String) {

    val customFontFamily = FontFamily(
        Font(R.font.dancingscript)
    )

    val stCol = Color(0xFF338F82)
    val col = Color(0xFFF1A892)

    val product = when (productId) {
        "P1" -> Product(
            id = "P1",
            title = "Dr.Althea – 345 Relief Cream",
            description = "Resveratrol 345NA – Intensive Repair Cream is a regenerating ointment gel cream rich in nutrients. The product strengthens the cell regeneration function in our skin for firmer skin while reducing the appearance of fine lines and wrinkles.",
            imageResId = R.drawable.dralthea,
            price = 990.99,
            category = "Hydrate Cream",
            quantity = 12
        )

        "P2" -> Product(
            id = "P2",
            title = "iUNIK – Centella Calming Gel Cream",
            description = "iUNIK Centella Calming Gel Cream is formulated with Centella Asiatica Leaf Water and Tea Tree Leaf Water to soothe and hydrate the skin.",
            imageResId = R.drawable.centella,
            price = 399.99,
            category = "Hydrate Cream",
            quantity = 5
        )

        "P3" -> Product(
            id = "P3",
            title = "Beauty of Joseon – Ground Rice and Honey Glow Serum",
            description = "Beauty of Joseon Ground Rice and Honey Glow Serum is a serum enriched with ground rice and honey, designed to enhance skin radiance and hydration.",
            imageResId = R.drawable.joseon,
            price = 595.90,
            category = "Serum",
            quantity = 20
        )

        "P4" -> Product(
            id = "P4",
            title = "Medicube PDRN Pink One Day Serum Set",
            description = "Packed with 99% salmon PDRN and collagen, this potent anti-aging ampoule restores skin elasticity and promotes skin cell regeneration.",
            imageResId = R.drawable.pdrn,
            price = 1500.00,
            category = "Serum",
            quantity = 10
        )

        "P5" -> Product(
            id = "P5",
            title = "Anua – Peach Niacin UV Tone Up",
            description = "This tone up sunscreen is fitted with SPF50+ PA++++ broad-spectrum sun protection. It’s formulated with peach extract, alongside Vitamin B12 and 20,000ppm of niacinamide to brighten skin and fade blemishes while boosting skin hydration.",
            imageResId = R.drawable.anua,
            price = 2600.00,
            category = "Sunscreen",
            quantity = 8
        )

        "P6" -> Product(
            id = "P6",
            title = "Beauty of Joseon – Sunscreen",
            description = "This organic Beauty of Joseon sunscreen is fitted with SPF 50+ PA++++ to fend off UVA and UVB rays. Lightweight formula comes in a quick-absorbent texture and doesn’t leave a white cast. Additional ingredients of rice extract and grain-derived probiotics keep skin supple and hydrated.",
            imageResId = R.drawable.relief,
            price = 500.90,
            category = "Sunscreen",
            quantity = 33
        )

        "P7" -> Product(
            id = "P7",
            title = "SKIN 1004 Madagascar Centella",
            description = "Blended with pink Himalayan salt and centella asiatica extract, this do-it-all clay stick mask soothes sensitive skin and purifies pores for clear and healthy skin. It features red bean powder to buff away dead skin cells for refined skin.",
            imageResId = R.drawable.centellasirom,
            price = 2000.00,
            category = "Mask",
            quantity = 29
        )

        "P8" -> Product(
            id = "P8",
            title = "Mary & May Calendula Peptide Ageless",
            description = "In order to reduce inflammation, heal damaged skin, and soothe irritations, this sleeping mask contains 2,200ppm of calendula extract, peptides, and cica extract. A boost of hydration is provided by an additional hyaluronic acid component.",
            imageResId = R.drawable.marymay,
            price = 3600.00,
            category = "Serum",
            quantity = 0
        )

        else -> Product(
            id = "?",
            title = "Produit inconnu",
            description = "Mafhamtch chno hada",
            imageResId = R.drawable.dralthea,
            price = 0.0,
            category = "Inconnu",
            quantity = 0
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(50.dp)
                .background(Color(0xFFF1A892)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "여자_SKIN",
                fontSize = 22.sp,
                fontFamily = customFontFamily,
                modifier = Modifier.padding(start = 16.dp)
            )

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Category",
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
                        .size(250.dp),
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
                            containerColor = col,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Panier"
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
}*/



