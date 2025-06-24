package com.example.ecommerceapp.data.Entities

import com.example.ecommerceapp.R

data class RoutineStep(val name: String, val imageRes: Int)
val routineSteps = listOf(
    RoutineStep("Toner", R.drawable.routine1),
    RoutineStep("Serum", R.drawable.routine2),
    RoutineStep("Cream", R.drawable.routine3),
    RoutineStep("Mask", R.drawable.routine4),
    RoutineStep("Sunscreen", R.drawable.routine5)
)

