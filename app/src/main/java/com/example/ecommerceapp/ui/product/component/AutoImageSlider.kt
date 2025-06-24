package com.example.ecommerceapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AutoImageSlider(images: List<Int>) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = currentIndex) {
        kotlinx.coroutines.delay(4000L)
        currentIndex = (currentIndex + 1) % images.size
        coroutineScope.launch {
            listState.animateScrollToItem(currentIndex)
        }
    }

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
            .padding(top = 0.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items(images.size) { index ->
            Image(
                painter = painterResource(id = images[index]),
                contentDescription = "Image $index",
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(top = 0.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
