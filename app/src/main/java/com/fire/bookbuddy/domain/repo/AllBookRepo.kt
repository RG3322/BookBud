package com.fire.bookbuddy.domain.repo

import com.fire.bookbuddy.common.BookCategoryModel
import com.fire.bookbuddy.common.BookModel
import com.fire.bookbuddy.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {

    fun getAllBooks(): Flow<ResultState<List<BookModel>>>
    fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>>
    fun getAllBooksByCategory(): Flow<ResultState<List<BookModel>>>


}