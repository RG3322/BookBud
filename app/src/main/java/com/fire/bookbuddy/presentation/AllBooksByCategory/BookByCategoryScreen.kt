package com.fire.bookbuddy.presentation.AllBooksByCategory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fire.bookbuddy.presentation.Effects.AnimateShimmer
import com.fire.bookbuddy.presentation.UIcomponent.Bookcart
import com.fire.bookbuddy.presentation.viewModel.ViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  BooksByCategoryScreen(
    category: String,navController: NavController,
    viewModel: ViewModel = hiltViewModel()
) {


    LaunchedEffect(Unit) {

        viewModel.BringAllCategory(category)


    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ,


    topBar = {
        TopAppBar(
            title = {
                Text( category)},
            navigationIcon = {

                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
                }
            }
        )



    }
    ){
        innerpadding ->
        val res = viewModel.state.value
        Column (modifier = Modifier.fillMaxSize()
            .padding(innerpadding)){

            when{
                res.isLoading ->{
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyColumn {
                            items(10){
                                AnimateShimmer()
                            }
                        }
                    }
                }
                res.error.isNotEmpty()->{
                    Text(text=res.error)
                }


                res.items.isNotEmpty() ->{
                    Column(modifier = Modifier.fillMaxSize()) {

                        LazyColumn(modifier = Modifier.fillMaxSize()) {

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
                }
                else -> Text("no books")
            }
        }




    }

}