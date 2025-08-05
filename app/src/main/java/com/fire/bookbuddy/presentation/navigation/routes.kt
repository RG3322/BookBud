package com.fire.bookbuddy.presentation.navigation

import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable


sealed class Routes{

    @Serializable
    object HomeScreen

    @Serializable
    data class BooksByCategory (val category: String, val navController: NavHostController){

    }

    @Serializable
    data class ShowPdfScreen(val url :String){

    }

}