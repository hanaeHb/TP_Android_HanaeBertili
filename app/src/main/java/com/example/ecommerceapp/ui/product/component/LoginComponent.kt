package com.example.ecommerceapp.ui.product.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Repository.UserRepository
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextAlign
import com.example.ecommerceapp.ui.product.Screen.AppLanguage

import com.example.ecommerceapp.ui.theme.LocalThemeState
import com.example.ecommerceapp.ui.theme.Mode


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    languageState: AppLanguage.Instance,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val customFontFamily = FontFamily(Font(R.font.dancingscript))

    var expanded by remember { mutableStateOf(false) }
    val themeState = LocalThemeState.current
    var expandedLang by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),) {

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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Box {
                    IconButton(onClick = { expanded = true }) {
                        androidx.compose.material3.Icon(
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
        LazyColumn(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                AutoBannerSlider(
                    images = listOf(
                        R.drawable.banner1,
                        R.drawable.banner2,
                        R.drawable.banner3
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        languageState.get("login"),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D0057),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(languageState.get("email")) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF907E36),
                            unfocusedBorderColor = Color(0xFFCCCCCC),
                            focusedLabelColor = Color(0xFF907E36),
                            cursorColor = Color(0xFF907E36)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(languageState.get("password")) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF907E36),
                            unfocusedBorderColor = Color(0xFFCCCCCC),
                            focusedLabelColor = Color(0xFF907E36),
                            cursorColor = Color(0xFF907E36)
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (UserRepository.isValidUser(email, password)) {
                                errorMessage = null
                                onLoginSuccess()
                            } else {
                                errorMessage = languageState.get("invalid_credentials")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D0057))
                    ) {
                        Text(languageState.get("login"), color = Color.White, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    LoginDividerWithText()
                    Spacer(modifier = Modifier.height(8.dp))

                    SocialRegisterButtons()

                    Spacer(modifier = Modifier.height(16.dp))
                    errorMessage?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(bottom = 45.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onNavigateToRegister) {
                        Text(languageState.get("no_account_register"), color = Color(0xFF907E36))
                    }
                }
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(bottom = 45.dp)
                .background(Color.Black),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(languageState.get("accept_terms"), color = Color(0xFFF5F4F8),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Text(languageState.get("privacy_policy"), color = Color(0xFFF5F4F8),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
            }
        }
    }
}
