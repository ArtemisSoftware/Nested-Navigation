package com.artemissoftware.nestednavigation.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.composables.NNSqueleton_3
import com.artemissoftware.nestednavigation.food.FoodConstants.getFood
import com.artemissoftware.nestednavigation.food.FoodConstants.randomFood

@Composable
fun FoodDetailsScreen(
    foodId: Int?,
    onNextFood: (Int) -> Unit,
) {
    val food = remember {
        getFood(foodId)
    }

    NNSqueleton_3 (
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp),
            ) {
                Image(
                    painter = painterResource(id = food.imageId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = food.title,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = food.subtitle,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier
                        .padding(24.dp),
                    onClick = {
                        onNextFood(randomFood().id)
                    },
                    content = {
                        Text(text = "Random Food")
                    },
                )
            }
        }
    )
}

@Preview
@Composable
private fun FoodDetailsScreenPreview() {
    FoodDetailsScreen(
        foodId = 1,
        onNextFood = {}
    )
}
