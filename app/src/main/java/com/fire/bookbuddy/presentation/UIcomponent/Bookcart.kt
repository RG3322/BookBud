package com.fire.bookbuddy.presentation.UIcomponent

import android.R.attr.fontWeight
import android.icu.text.CaseMap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.navigation.NavDestination.Companion.createRoute
import coil.compose.SubcomposeAsyncImage
import com.fire.bookbuddy.presentation.navigation.Routes
import java.net.URL

@Composable
fun Bookcart(
    imageURL: String,
    title: String,
    author: String,
    description: String,
    bookUrl: String,
    navController: NavController
) {

    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .clickable(){
                navController.navigate(Routes.ShowPdfScreen(url = bookUrl))
                }

    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
                .height(150.dp)
                .fillMaxWidth()




        ) {
            SubcomposeAsyncImage(
                model = imageURL,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
                    .size(100.dp)
                    .clip(  RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                loading = {
                    //useimage
                },
                error = {
                    Text(text = "Image Error")
                }


            )
            Spacer(modifier= Modifier.width(8.dp))

            Column(){

                Text(text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))


                Text(

                    text = "Author: $author",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp),


                    )
                Spacer(modifier = Modifier.height(8.dp))


            }





        }

    }


}