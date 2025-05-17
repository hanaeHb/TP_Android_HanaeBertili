package com.example.ecommerceapp.data.Repository

import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Entities.Product
import kotlinx.coroutines.delay

class ProductRepository {
    suspend fun getProducts(): List<Product> {

        delay(2000)
        return listOf(
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
    }
}