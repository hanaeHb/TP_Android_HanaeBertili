package com.example.ecommerceapp.data.Entities

import com.example.ecommerceapp.R

data class RoutineStep(val name: String, val imageRes: Int)
val routineSteps = listOf(
    RoutineStep("Select your skin type", R.drawable.types),
    RoutineStep("1.Toner", R.drawable.routine1),
    RoutineStep("2.Serum", R.drawable.routine2),
    RoutineStep("3.Mask", R.drawable.routine3),
    RoutineStep("4.Moisturizer", R.drawable.routine4),
    RoutineStep("5.Sunscreen", R.drawable.routine5)
)

