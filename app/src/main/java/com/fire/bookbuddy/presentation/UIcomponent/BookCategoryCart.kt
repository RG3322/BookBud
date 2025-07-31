package com.fire.bookbuddy.presentation.UIcomponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage

// do it later, time stamp 2:16:00
@Composable
fun BookCategoryCart(
    category: String,
    navController: NavController,
    imageURl: String,
){

    Card(
        onClick = {
            navController.navigate("book_category")
        },
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .height(150.dp)

    ) {
        Column(modifier = Modifier.padding(8.dp)
            .size(8.dp)
            , horizontalAlignment = Alignment.CenterHorizontally) {

            SubcomposeAsyncImage(
                model = imageURl,
                contentDescription = category,
                modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                loading = {

                },
                error = {

                        Text(text = "Image Error")

                }

            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = category,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

        }
    }

}
