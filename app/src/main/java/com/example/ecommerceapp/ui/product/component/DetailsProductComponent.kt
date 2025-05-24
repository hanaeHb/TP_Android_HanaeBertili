package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Product

@Composable
fun DetailsScreen(productId: String) {

    val customFontFamily = FontFamily(
        Font(R.font.dancingscript)
    )

    val stCol = Color(0xFF338F82)
    val col = Color(0xFF907E36)
    val colM = Color(0xFFE6E6FA)

    val product = when (productId) {
        "P1" -> Product(
            id = "P1",
            title = "Dr.Althea – 345 Relief Cream",
            description = "Resveratrol 345NA – Intensive Repair Cream is a regenerating ointment gel cream rich in nutrients. The product strengthens the cell regeneration function in our skin for firmer skin while reducing the appearance of fine lines and wrinkles.",
            imageResId = R.drawable.medicup,
            price = 990.99,
            category = "Hydrate Cream",
            quantity = 12
        )

        "P2" -> Product(
            id = "P2",
            title = "iUNIK – Centella Calming Gel Cream",
            description = "iUNIK Centella Calming Gel Cream is formulated with Centella Asiatica Leaf Water and Tea Tree Leaf Water to soothe and hydrate the skin.",
            imageResId = R.drawable.snail,
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
                        .size(250.dp)
                        .clip(RoundedCornerShape(24.dp)),
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
                            containerColor = colM,
                            contentColor = col
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "cart"
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
}