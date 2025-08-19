package com.fire.bookbuddy.presentation.AllBooksByCategory

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
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
                Text(text = category)
            }
        )



    }
    ){

    }

}