package com.fire.bookbuddy.presentation.UIcomponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
            , horizontalAlignment = Alignment.CenterHorizontally) {  }
    }

}
//nothing much bit  i  need that green stuff