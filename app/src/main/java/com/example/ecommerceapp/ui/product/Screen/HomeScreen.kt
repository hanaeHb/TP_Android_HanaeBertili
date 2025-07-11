package com.example.ecommerceapp.ui.product.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.product.ProductIntent
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.component.ProductCard
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.text.font.FontWeight
import com.example.ecommerceapp.data.Entities.Product
import com.example.ecommerceapp.ui.product.getEffectivePrice
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import com.example.ecommerceapp.data.Entities.routineSteps
import com.example.ecommerceapp.ui.product.component.AutoImageSlider
import com.example.ecommerceapp.ui.product.component.RoutineStepCard
import com.example.ecommerceapp.ui.product.component.SocialMediaSection
import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode
import com.example.ecommerceapp.ui.theme.ThemeState

@Composable
fun HomeScreen(viewModel: ProductViewModel, onProductClick: (String) -> Unit, onNavigateCart: () -> Unit,
               onNavigateLogin: () -> Unit, onNavigateFavorite: () -> Unit, onNavigateCategory: () -> Unit,  languageState: AppLanguage.Instance,) {
    val state by viewModel.state.collectAsState()

    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var filterByPromotion by remember { mutableStateOf(false) }
    var sortByPriceAscending by remember { mutableStateOf<Boolean?>(null) }
    var filterByOfferEndingSoon by remember { mutableStateOf(false) }
    var selectedBrand by remember { mutableStateOf<String?>(null) }
    var brandMenuExpanded by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    var expandedLang by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (state.products.isEmpty()) {
            viewModel.handleIntent(ProductIntent.LoadProducts)
        }
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                .height(50.dp)
                .background(MaterialTheme.colorScheme.surface),
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

        when {
            state.isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Loading products...", color = Color(0xFF907E36))
                }
            }

            state.error != null -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error: ${state.error}", color = Color.Red)
                }
            }

            else -> {
                var priceMenuExpanded by remember { mutableStateOf(false) }
                val filteredProducts = state.products
                    .filter { product ->
                        (selectedCategory == null || product.category == selectedCategory) &&
                                (searchQuery.isBlank() || product.title.contains(searchQuery, ignoreCase = true)) &&
                                (!filterByPromotion || (product.discountPercentage ?: 0) > 0) &&
                                (selectedBrand == null || product.brand == selectedBrand)
                    }
                    .sortedWith(
                        compareBy<Product> { product ->

                            if (filterByOfferEndingSoon) product.offerEnd ?: Long.MAX_VALUE else Long.MAX_VALUE
                        }.thenComparator { a, b ->

                            if (sortByPriceAscending == null) 0
                            else {
                                val priceA = a.getEffectivePrice()
                                val priceB = b.getEffectivePrice()
                                if (sortByPriceAscending == true)
                                    priceA.compareTo(priceB)
                                else
                                    priceB.compareTo(priceA)
                            }
                        }
                    )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f)
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 64.dp)
                ) {

                    item(span = { GridItemSpan(2) }) {
                        AutoImageSlider(
                            images = listOf(
                                R.drawable.bestskincare,
                                R.drawable.bestskincare2,
                                R.drawable.bestskincare3
                            )
                        )
                    }
                    item(span = { GridItemSpan(2) }) {
                        var brandMenuExpanded by remember { mutableStateOf(false) }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                                .border(
                                    width = 1.5.dp,
                                    color = Color(0xFF907E36),
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .clip(RoundedCornerShape(24.dp))
                                .background(Color.White)
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search Icon",
                                        tint = Color(0xFF907E36),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(1.dp))
                                    TextField(
                                        value = searchQuery,
                                        onValueChange = { searchQuery = it },
                                        placeholder = { Text(languageState.get("search"), color = Color(0xFF907E36),fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium) },
                                        colors = TextFieldDefaults.colors(
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent,
                                            focusedContainerColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            disabledContainerColor = Color.Transparent,
                                            cursorColor = Color.Black,
                                            focusedTextColor = Color.Black,
                                            unfocusedTextColor = Color.Black,
                                            disabledTextColor = Color.Gray
                                        ),
                                        modifier = Modifier.width(180.dp),
                                        maxLines = 1,
                                        singleLine = true
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(45.dp)
                                        .width(1.4.dp)
                                        .background(Color(0xFFE6E6FA))
                                )
                                Box {
                                    Row(
                                        modifier = Modifier
                                            .clickable { brandMenuExpanded = true }
                                            .padding(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Tune,
                                            contentDescription = "Filter Icon",
                                            tint = Color(0xFF907E36),
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = languageState.get("filters"),
                                            color = Color(0xFF907E36),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = brandMenuExpanded,
                                        onDismissRequest = { brandMenuExpanded = false },
                                        modifier = Modifier.background(Color.White)
                                    ) {

                                        DropdownMenuItem(
                                            text = {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Box(
                                                        modifier = Modifier
                                                            .size(55.dp)
                                                            .clip(CircleShape)
                                                            .border(0.3.dp, Color.Transparent, CircleShape),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Icon(
                                                            imageVector = Icons.Default.Star,
                                                            contentDescription = "All",
                                                            tint = Color(0xFF907E36),
                                                            modifier = Modifier.size(19.dp)
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                    Text("All Brands", color = Color(0xFF907E36), fontSize = 17.sp)
                                                }
                                            },
                                            onClick = {
                                                selectedBrand = null
                                                brandMenuExpanded = false
                                            }
                                        )

                                        Divider()


                                        val brands = state.products.mapNotNull { it.brand }.distinct()
                                        brands.forEachIndexed { index, brand ->
                                            val productWithBrand = state.products.find { it.brand == brand }
                                            val imageResId = productWithBrand?.imageBrand?.let { imageName ->
                                                val context = LocalContext.current
                                                remember(imageName) {
                                                    context.resources.getIdentifier(imageName, "drawable", context.packageName)
                                                }
                                            }

                                            DropdownMenuItem(
                                                text = {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        if (imageResId != null && imageResId != 0) {
                                                            Image(
                                                                painter = painterResource(id = imageResId),
                                                                contentDescription = brand,
                                                                modifier = Modifier
                                                                    .size(55.dp)
                                                                    .clip(CircleShape)
                                                                    .border(0.3.dp, Color.Transparent, CircleShape)
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.width(8.dp))
                                                        Text(
                                                            text = brand,
                                                            color = if (selectedBrand == brand) Color(0xFF907E36) else Color(0xFF1D0057),
                                                            fontSize = 17.sp
                                                        )
                                                    }
                                                },
                                                onClick = {
                                                    selectedBrand = brand
                                                    brandMenuExpanded = false
                                                }
                                            )

                                            if (index != brands.lastIndex) Divider()
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item(span = { GridItemSpan(2) }){
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = languageState.get("skin_care"),
                                    fontSize = 25.sp,
                                    fontFamily = customFontFamily,
                                    color = Color(0xFF1D0057)
                                )
                            }

                            Divider(
                                color = Color(0xFF1D0057),
                                thickness = 0.5.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                            )
                        }
                    }
                    item(span = { GridItemSpan(2) }) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                listOf(
                                    Pair(R.drawable.fav6, languageState.get("all")),
                                    Pair(R.drawable.sunscreen1, "Sunscreen"),
                                    Pair(R.drawable.serumm, "Serum"),
                                    Pair(R.drawable.mask, "Mask"),
                                    Pair(R.drawable.cream, "Hydrate Cream"),
                                    Pair(R.drawable.toner2, "Toner"),
                                ).forEach { (iconRes, label) ->
                                    val isSelected = selectedCategory == label || (label == languageState.get("all") && selectedCategory == null)
                                    val scale by animateFloatAsState(if (isSelected) 1.1f else 1f)

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .padding(top = 30.dp, start= 7.dp)
                                            .scale(scale)
                                            .clickable {
                                                selectedCategory = if (label == languageState.get("all")) null else label
                                            }
                                    ) {
                                        Image(
                                            painter = painterResource(id = iconRes),
                                            contentDescription = label,
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(CircleShape)
                                                .background(
                                                    if (isSelected) Color(0xFFDCD0FF) else Color(0xFFECECEC),
                                                    shape = CircleShape
                                                )
                                                .border(
                                                    width = if (isSelected) 2.6.dp else 2.3.dp,
                                                    color = if (isSelected) Color(0xFF907E36) else Color(0xFFE6E6FA),
                                                    shape = CircleShape
                                                )
                                                .padding(1.dp),
                                            contentScale = ContentScale.Crop
                                        )

                                        Text(
                                            text = label,
                                            color = if (isSelected) Color.Black else Color(0xFF907E36),
                                            fontSize = if (isSelected) 16.sp else 14.sp,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }
                                }
                            }

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 20.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                item {
                                    FilterChip(
                                        selected = filterByPromotion,
                                        onClick = { filterByPromotion = !filterByPromotion },
                                        label = { Text(languageState.get("offers")) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = Color(0xFF907E36),
                                            selectedLabelColor = Color.White,
                                            containerColor = Color(0xFFECECEC),
                                            labelColor = Color(0xFF907E36)
                                        )
                                    )
                                }

                                item {
                                    Box {
                                        FilterChip(
                                            selected = sortByPriceAscending != null,
                                            onClick = { priceMenuExpanded = true },
                                            label = {
                                                Text(
                                                    text = when (sortByPriceAscending) {
                                                        true -> languageState.get("Priceh")
                                                        false -> languageState.get("Priceb")
                                                        null -> languageState.get("Sort")
                                                    }
                                                )
                                            },
                                            colors = FilterChipDefaults.filterChipColors(
                                                selectedContainerColor = Color(0xFF907E36),
                                                selectedLabelColor = Color.White,
                                                containerColor = Color(0xFFECECEC),
                                                labelColor = Color(0xFF907E36)
                                            )
                                        )

                                        DropdownMenu(
                                            expanded = priceMenuExpanded,
                                            onDismissRequest = { priceMenuExpanded = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = { Text(languageState.get("Low")) },
                                                onClick = {
                                                    sortByPriceAscending = true
                                                    priceMenuExpanded = false
                                                }
                                            )
                                            DropdownMenuItem(
                                                text = { Text(languageState.get("High")) },
                                                onClick = {
                                                    sortByPriceAscending = false
                                                    priceMenuExpanded = false
                                                }
                                            )
                                            DropdownMenuItem(
                                                text = { Text(languageState.get("Without")) },
                                                onClick = {
                                                    sortByPriceAscending = null
                                                    priceMenuExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }

                                item {
                                    FilterChip(
                                        selected = filterByOfferEndingSoon,
                                        onClick = { filterByOfferEndingSoon = !filterByOfferEndingSoon },
                                        label = { Text(languageState.get("Ending Soon")) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = Color(0xFF907E36),
                                            selectedLabelColor = Color.White,
                                            containerColor = Color(0xFFECECEC),
                                            labelColor = Color(0xFF907E36)
                                        )
                                    )
                                }
                            }

                        }
                    }
                    item(span = { GridItemSpan(2) }) {
                        Text(
                            text = languageState.get("Products for you my lady"),
                            color = Color(0xFF907E36),
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp, top = 20.dp)
                        )
                    }
                    item(span = { GridItemSpan(2) }){
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = languageState.get("Featured Products >"),
                                    fontSize = 25.sp,
                                    fontFamily = customFontFamily,
                                    color = Color(0xFF1D0057)
                                )
                            }

                            Divider(
                                color = Color(0xFF1D0057),
                                thickness = 0.5.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                            )
                        }
                    }
                    items(filteredProducts) { product ->
                        ProductCard(viewModel = viewModel, product = product, onClick = { onProductClick(product.id) })
                    }

                    item(span = { GridItemSpan(2) }) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 75.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = languageState.get("The right skin care routine"),
                                fontSize = 20.sp,
                                color = Color(0xFF1D0057),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(
                                color = Color(0xFF1D0057),
                                thickness = 0.5.dp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }


                    item(span = { GridItemSpan(2) }){
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            itemsIndexed(routineSteps) { index, step ->
                                RoutineStepCard(step, onClick = {
                                    onNavigateCategory()})

                                if (index < routineSteps.lastIndex) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow),
                                        contentDescription = "Arrow",
                                        tint = Color(0xFF1D0057),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                        }
                    }

                    item(span = { GridItemSpan(2) }) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = languageState.get("MORE OF OUR SOCIALS"),
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(
                                color = Color.Black,
                                thickness = 1.2.dp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }

                    item(span = { GridItemSpan(2) }) {
                        SocialMediaSection()
                    }
                }
            }
        }

        val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
        val favoriteCount = viewModel.state.collectAsState().value.products.count { it.isFavorite }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp)
                .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val inactiveColor = Color(0xFF1D0057)
            Box(
                modifier = Modifier.size(40.dp)
            ){
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home",
                    tint = inactiveColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(25.dp))
            }
            Box(modifier = Modifier
                .size(40.dp)
                .clickable { onNavigateFavorite() }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color(0xFF907E36),
                    modifier = Modifier
                        .align(Alignment.Center)
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
                    modifier = Modifier
                        .align(Alignment.Center)
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
