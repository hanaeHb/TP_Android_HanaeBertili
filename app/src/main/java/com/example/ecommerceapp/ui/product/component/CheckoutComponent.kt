package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R

import com.example.ecommerceapp.ui.product.ProductViewModel

@Composable
fun CheckoutScreen(viewModel: ProductViewModel, onNavigateCart: () -> Unit, onNavigateFavorite: () -> Unit, onNavigateCategory: () -> Unit, onNavigateHome: () -> Unit) {

    val cartItems = viewModel.state.collectAsState().value.cartItems
    var email by remember { mutableStateOf("") }
    var newsChecked by remember { mutableStateOf(true) }
    var country by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }

    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var securityCode by remember { mutableStateOf("") }
    var nameOnCard by remember { mutableStateOf("") }
    var billingSameAsShipping by remember { mutableStateOf(true) }
    val customFontFamily = FontFamily(Font(R.font.dancingscript))
    val totalQuantity = cartItems.sumOf { it.quantity }
    val totalPrice = cartItems.sumOf { item ->
        val price = item.product.price.toDoubleOrNull() ?: 0.0
        val discount = item.product.discountPercentage ?: 0
        val discountedPrice = price * (1 - discount / 100.0)
        discountedPrice * item.quantity
    }

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
            androidx.compose.material3.Text(
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
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Checkout >",
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
                Text("Contact", fontSize = 18.sp)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = newsChecked, onCheckedChange = { newsChecked = it })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Email me with news and offers")
                }
            }


            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Delivery", fontSize = 18.sp)
                OutlinedTextField(
                    value = country,
                    onValueChange = { country = it },
                    label = { Text("Country/Region", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
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
                    label = { Text("First name", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
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
                    label = { Text("Last name", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = company,
                    onValueChange = { company = it },
                    label = { Text("Company (optional)", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
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
                    label = { Text("Address", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
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
                    label = { Text("Postal Code", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
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
                Text("Shipping Method", fontSize = 18.sp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                        .padding(12.dp)
                ) {
                    Text("Enter your shipping address to view available shipping methods.")
                }
            }


            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Payment", fontSize = 18.sp, fontWeight = FontWeight.Bold)

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
                    label = { Text("Card number", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = expiry,
                    onValueChange = { expiry = it },
                    label = { Text("Expiration date (MM / YY)", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF907E36),
                        unfocusedBorderColor = Color(0xFFCCCCCC),
                        focusedLabelColor = Color(0xFF1D0057),
                        cursorColor = Color(0xFF1D0057)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = securityCode,
                    onValueChange = { securityCode = it },
                    label = { Text("Security code", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
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
                    label = { Text("Name on card", fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
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
                    Text("Use shipping address as billing address")
                }
            }


            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text("Order Summary", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            items(cartItems) { cartItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
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
                        Text(cartItem.product.title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(8.dp))
                        val price = cartItem.product.price.toDoubleOrNull() ?: 0.0
                        val discount = cartItem.product.discountPercentage ?: 0
                        val discountedPrice = price * (1 - discount / 100.0)
                        Text("${cartItem.quantity} x $${"%.2f".format(discountedPrice)}", fontSize = 14.sp)
                    }
                }
            }

            item {
                Divider(
                    color = Color(0xFF1D0057),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
                Text("$totalQuantity items", fontWeight = FontWeight.Medium, fontSize = 18.sp, color = Color(0xFF1D0057))
                Spacer(modifier = Modifier.height(16.dp))
                Text("Total: $${"%.2f".format(totalPrice)}", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF1D0057))
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF907E36))
                ) {
                    Text("Pay", color = Color.White, fontSize = 18.sp)
                }
            }
        }

        val totalCartItems = viewModel.state.collectAsState().value.cartItems.size
        val favoriteCount = viewModel.state.collectAsState().value.products.count { it.isFavorite }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 45.dp),
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
