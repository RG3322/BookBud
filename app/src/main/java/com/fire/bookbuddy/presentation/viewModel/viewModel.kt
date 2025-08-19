package com.fire.bookbuddy.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.fire.bookbuddy.common.BookCategoryModel
import com.fire.bookbuddy.common.BookModel
import com.fire.bookbuddy.common.ResultState
import com.fire.bookbuddy.domain.repo.AllBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ViewModel @Inject constructor(private val repo: AllBookRepo) : androidx.lifecycle.ViewModel() {



    private val _state : MutableState<ItemState> = mutableStateOf(ItemState())
    val state : MutableState<ItemState> = _state




    fun BringAllBooks(){
        viewModelScope.launch {

            repo.getAllBooks().collect{
                when(it){
                    is ResultState.Error<*> -> _state.value = ItemState(error = it.exception.toString())
                    ResultState.Loading ->  _state.value = ItemState(isLoading = true)
                    is ResultState.Success<*> -> _state.value = ItemState(items = it.data as List<BookModel>)
                }

            }

        }
    }

    fun BringAllCategory(category: String) {

        viewModelScope.launch {
            repo.getAllCategory().collect{
                when(it){
                    is ResultState.Error<*> -> _state.value = ItemState(error = it.exception.toString())
                    ResultState.Loading ->  _state.value = ItemState(isLoading = true)
                    is ResultState.Success<*> -> _state.value = ItemState(category = it.data as List<BookCategoryModel>)
                }
            }
        }

    }













}


data class ItemState(

    val isLoading: Boolean = false,
    val items: List<BookModel> = emptyList(),
    val error: String = "",
    val category: List<BookCategoryModel> = emptyList()




)