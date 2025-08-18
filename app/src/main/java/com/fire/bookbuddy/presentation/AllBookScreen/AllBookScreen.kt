package com.fire.bookbuddy.presentation.AllBookScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fire.bookbuddy.presentation.Effects.AnimateShimmer
import com.fire.bookbuddy.presentation.UIcomponent.Bookcart
import com.fire.bookbuddy.presentation.viewModel.ViewModel

@Composable
fun AllBookScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ViewModel = hiltViewModel()
)

{
    LaunchedEffect(Unit) {
        viewModel.BringAllBooks()
    }

    val res = viewModel.state.value

    when{
        res.isLoading ->{
            Column(modifier = Modifier.fillMaxSize()){
                LazyColumn {

                    items(10){
                        AnimateShimmer()
                    }





                }
            }



        }
        res.items.isNotEmpty() ->{


            Column(modifier = Modifier.fillMaxSize()){
                LazyColumn(modifier = modifier.fillMaxSize()) {

                    items(res.items){
                        Bookcart(
                            imageURL = it.image,
                            title = it.bookName,
                            author = it.bookAuthor,
                            description = it.bookDescription,
                            bookUrl = it.bookUrl,
                            navController = navController
                        )
                    }


                }
            }

        }else-> {
            Text(text= "No books available at the moment")
        }
    }

}