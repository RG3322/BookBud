package com.fire.bookbuddy.presentation.CategoryScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fire.bookbuddy.presentation.Effects.categoryShimmer
import com.fire.bookbuddy.presentation.UIcomponent.BookCategoryCard
import com.fire.bookbuddy.presentation.viewModel.ViewModel


@Composable
fun CategoryScreen(
    viewmodel: ViewModel = hiltViewModel(),
    navHostController: NavHostController) {

    LaunchedEffect(Unit) {

        viewmodel.BringAllCategory()


    }
    Column(modifier = Modifier.fillMaxSize()) {


        val res = viewmodel.state.value
        when {
            res.isLoading -> {
                Column(modifier = Modifier.fillMaxSize()) {

                    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                        items(10) {
                            categoryShimmer()
                        }
                    }

                }
            }

            res.error.isNotEmpty() -> {

                Text(text = res.error)

            }

            res.category.isNotEmpty() -> {
                LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items(res.category) {
                        BookCategoryCard(
                            imageURl = it.categoryURL,
                            category = it.name,
                            navController = navHostController


                        )
                    }
                }


            }


        }
    }
}