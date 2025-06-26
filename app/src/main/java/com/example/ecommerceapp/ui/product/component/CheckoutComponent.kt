package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Client
import androidx.compose.animation.core.*
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.ecommerceapp.ui.product.ProductViewModel
import com.example.ecommerceapp.ui.product.Screen.AppLanguage

import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode
import kotlinx.coroutines.launch

@Composable
fun CheckoutScreen(viewModel: ProductViewModel, onNavigateCart: () -> Unit, onNavigateFavorite: () -> Unit,
                   onNavigateCategory: () -> Unit, onNavigateHome: () -> Unit, onNavigateTrack: () -> Unit,  languageState: AppLanguage.Instance,) {

    val cartItems = viewModel.state.collectAsState().value.cartItems
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var newsChecked by remember { mutableStateOf(true) }
    var country by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }

    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var securityCode by remember { mutableStateOf("") }
    var nameOnCard by remember { mutableStateOf("") }
    var billingSameAsShipping by remember { mutableStateOf(true) }
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    val totalQuantity = cartItems.size
    val totalPrice = cartItems.sumOf { item ->
        val price = item.product.price.toDoubleOrNull() ?: 0.0
        val discount = item.product.discountPercentage ?: 0
        val discountedPrice = price * (1 - discount / 100.0)
        discountedPrice * item.quantity
    }
    var selectedIndex by remember { mutableStateOf(-1) }
    var showSecurityCode by remember { mutableStateOf(false) }
    fun isFormValid(): Boolean {
        return email.isNotBlank() &&
                firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                address.isNotBlank() &&
                postalCode.isNotBlank() &&
                cardNumber.isNotBlank() &&
                expiry.isNotBlank() &&
                securityCode.isNotBlank() &&
                nameOnCard.isNotBlank() &&
                selectedIndex != -1
    }
    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    var expandedLang by remember { mutableStateOf(false) }
    viewModel.setClientInfo(
        Client(
            email = email,
            country = country,
            firstName = firstName,
            lastName = lastName,
            address = address,
            postalCode = postalCode
        )
    )
    val options = listOf(
        Triple(R.drawable.gift1, R.drawable.package1, "Beige"),
        Triple(R.drawable.gift2, R.drawable.package2, "Black"),
        Triple(R.drawable.gift3, R.drawable.package3, "Purple"),
        Triple(R.drawable.gift4, R.drawable.package4, "Pink")
    )
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    var expiryError by remember { mutableStateOf(false) }

    fun isValidExpiryDate(expiry: String): Boolean {
        val regex = Regex("""^(0[1-9]|1[0-2])\s?/\s?([0-9]{2})$""")
        if (!regex.matches(expiry)) return false
        return true
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
            androidx.compose.material3.Text(
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
                    androidx.compose.material3.IconButton(onClick = { expanded = true }) {
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
                    androidx.compose.material3.IconButton(onClick = { expandedLang = true }) {
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
                            text = { androidx.compose.material3.Text("العربية") },
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
            Text(
                text = languageState.get("Checkout >"),
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

            item {
                Text(languageState.get("Contact"), fontSize = 18.sp)
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = !isValidEmail(email)
                    },
                    label = { Text(languageState.get("email"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = emailError,
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = if (emailError) Color.Red else Color(0xFF907E36),
                        unfocusedBorderColor = if (emailError) Color.Red else Color(0xFFCCCCCC),
                        focusedLabelColor = if (emailError) Color.Red else Color(0xFF1D0057),
                        cursorColor = if (emailError) Color.Red else Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                if (emailError) {
                    Text(
                        text = languageState.get("Please enter a valid email address"),
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = newsChecked, onCheckedChange = { newsChecked = it })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(languageState.get("Email me with news and offers"))
                }
            }


            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(languageState.get("Delivery"), fontSize = 18.sp)
                OutlinedTextField(
                    value = country,
                    onValueChange = { country = it },
                    label = { Text(languageState.get("Country/Region"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(languageState.get("first_name"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text(languageState.get("last_name"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text(languageState.get("phone"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text(languageState.get("Address"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = postalCode,
                    onValueChange = { postalCode = it },
                    label = { Text(languageState.get("Postal Code"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(languageState.get("Select the gift and the package you want"), fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    options.forEachIndexed { index, (giftRes, packageRes, colorName) ->
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if (selectedIndex == index) Color(0xFF907E36).copy(alpha = 0.1f)
                                        else Color.White
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = if (selectedIndex == index) Color(0xFF907E36) else Color.Gray,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        selectedIndex = index
                                        viewModel.setSelectedGiftIndex(selectedIndex)
                                    }
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ImageWithLabel(imageResId = giftRes)
                                ImageWithLabel(imageResId = packageRes)
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "$colorName Gift and Package",
                                fontSize = 18.sp,
                                color = if (selectedIndex == index) Color(0xFF907E36) else Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(color = Color(0xFFCCCCCC), thickness = 0.5.dp)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(languageState.get("Shipping Method"), fontSize = 18.sp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                        .padding(12.dp)
                ) {
                    Text(languageState.get("Enter your shipping address to view available shipping methods."))
                }
            }


            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(languageState.get("Payment"), fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PaymentOptionIcon(R.drawable.paypal, "PayPal")
                    PaymentOptionIcon(R.drawable.visa, "Visa")
                    PaymentOptionIcon(R.drawable.card, "Mastercard")
                }

                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text(languageState.get("Card number"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = expiry,
                    onValueChange = {
                        expiry = it
                        expiryError = !isValidExpiryDate(expiry)
                    },
                    label = { Text(languageState.get("Expiration date (MM / YY)"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = expiryError,
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = if (expiryError) Color.Red else Color(0xFF907E36),
                        unfocusedBorderColor = if (expiryError) Color.Red else Color(0xFFCCCCCC),
                        focusedLabelColor = if (expiryError) Color.Red else Color(0xFF1D0057),
                        cursorColor = if (expiryError) Color.Red else Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                if (expiryError) {
                    Text(
                        text = languageState.get("Enter a valid expiry date (MM / YY)"),
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }

                OutlinedTextField(
                    value = securityCode,
                    onValueChange = { securityCode = it },
                    label = { Text(languageState.get("Security code"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    visualTransformation = if (showSecurityCode) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (showSecurityCode) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        IconButton(onClick = { showSecurityCode = !showSecurityCode }) {
                            Icon(imageVector = icon, contentDescription = if (showSecurityCode) "Hide" else "Show")
                        }
                    },
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = nameOnCard,
                    onValueChange = { nameOnCard = it },
                    label = { Text(languageState.get("Name on card"), fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = billingSameAsShipping, onCheckedChange = { billingSameAsShipping = it })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(languageState.get("Use shipping address as billing address"))
                }
            }


            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(languageState.get("Order Summary"), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Divider(
                    color = Color(0xFF1D0057),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                if (selectedIndex != -1) {
                    val (giftRes, packageRes, colorName) = options[selectedIndex]
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = languageState.get("Your Package and your gift"),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF1D0057),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = giftRes),
                                contentDescription = "Selected Gift",
                                modifier = Modifier.size(150.dp)
                            )
                            Image(
                                painter = painterResource(id = packageRes),
                                contentDescription = "Selected Package",
                                modifier = Modifier.size(150.dp)
                            )
                        }
                        Text(
                            text = "$colorName Gift and Package",
                            fontSize = 16.sp,
                            color = Color(0xFF907E36),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }


            item{
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = languageState.get("Your products"),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1D0057),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            items(cartItems) { cartItem ->
                Column(
                    modifier = Modifier.fillMaxWidth(),

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .background(Color.White, shape = RoundedCornerShape(4.dp)),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val imageResId = getImageResIdByName(cartItem.product.imageResId)
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = cartItem.product.title,
                            modifier = Modifier
                                .size(90.dp)
                                .padding(end = 12.dp)
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                cartItem.product.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            val price = cartItem.product.price.toDoubleOrNull() ?: 0.0
                            val discount = cartItem.product.discountPercentage ?: 0
                            val discountedPrice = price * (1 - discount / 100.0)
                            Text(
                                "${cartItem.quantity} x $${"%.2f".format(discountedPrice)}",
                                fontSize = 14.sp
                            )
                        }
                    }
                    Divider(
                        color = Color(0xFF1D0057),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
            }

            item {
                Text("$totalQuantity items", fontWeight = FontWeight.Medium, fontSize = 18.sp, color = Color(0xFF1D0057))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Price total of products: $${"%.2f".format(totalPrice)}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color(0xFF1D0057)
                )
            }
            item {
                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F2F2)),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        val deliveryFee = 5.50
                        val totalWithDelivery = totalPrice + deliveryFee

                        Text(
                            languageState.get("Delivery: $5.50"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF1D0057)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Total: $${"%.2f".format(totalWithDelivery) + 5.50}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF1D0057)
                        )
                    }
                }
            }

            item {
                var showValidationError by remember { mutableStateOf(false) }

                val shakeOffset = remember { Animatable(0f) }

                LaunchedEffect(showValidationError) {
                    if (showValidationError) {
                        shakeOffset.animateTo(
                            targetValue = 10f,
                            animationSpec = repeatable(
                                iterations = 3,
                                animation = tween(durationMillis = 100, easing = LinearEasing),
                                repeatMode = RepeatMode.Reverse
                            )
                        )
                        shakeOffset.snapTo(0f)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (isFormValid()) {
                            viewModel.checkoutAndClearCart()
                            onNavigateTrack()
                        } else {
                            showValidationError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                        .offset(x = shakeOffset.value.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF907E36))
                ) {
                    Text(languageState.get("Pay"), color = Color.White, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (showValidationError) {
                    Text(
                        text = languageState.get("empty_fields"),
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
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
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val inactiveColor = Color(0xFF907E36)
            Box(
                modifier = Modifier.size(40.dp)
            ){
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home",
                    tint = inactiveColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { onNavigateHome() }
                )
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
                        androidx.compose.material3.Text(
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
                        androidx.compose.material3.Text(
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
@Composable
fun ImageWithLabel(imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier
            .size(140.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

