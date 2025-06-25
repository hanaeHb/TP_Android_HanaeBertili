package com.example.ecommerceapp.ui.product.component
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
@Composable
fun OrderStatusTracker() {
    val stages = listOf(
        Pair(Icons.Filled.CheckCircle, "Order Placed"),
        Pair(Icons.Filled.Build, "Preparing your order"),
        Pair(Icons.Filled.LocalShipping, "Shipped"),
        Pair(Icons.Filled.TwoWheeler, "Out for Delivery")
    )

    val totalStages = stages.size
    val totalDurationMs = totalStages * 10_000L
    val stepDelayMs = 100L
    val steps = (totalDurationMs / stepDelayMs).toInt()
    val increment = 1f / steps

    var progress by remember { mutableStateOf(0f) }
    var finished by remember { mutableStateOf(false) }

    LaunchedEffect(finished) {
        if (!finished) {
            progress = 0f
            repeat(steps) {
                delay(stepDelayMs)
                progress += increment
            }
            progress = 1f
            finished = true
        }
    }

    val currentStageIndex = (progress * totalStages).toInt().coerceIn(0, totalStages - 1)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!finished) {
            Icon(
                imageVector = stages[currentStageIndex].first,
                contentDescription = stages[currentStageIndex].second,
                tint = Color(0xFF1D0057),
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stages[currentStageIndex].second,
                fontSize = 19.sp,
                color = Color(0xFF1D0057)
            )
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = progress.coerceIn(0f, 1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color(0xFF907E36),
                trackColor = Color(0xFFD7CDAF)
            )
        } else {
            Text(
                text = "Your order will arrive within 48 hours",
                fontSize = 18.sp,
                color = Color(0xFF907E36)
            )
        }
    }
}
