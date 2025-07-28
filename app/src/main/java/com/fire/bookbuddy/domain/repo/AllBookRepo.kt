package com.fire.bookbuddy.domain.repo

import com.fire.bookbuddy.common.BookCategoryModel
import com.fire.bookbuddy.common.BookModel
import com.fire.bookbuddy.common.ResultState
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {

    fun getAllBooks(): Flow<ResultState<List<BookModel>>>
    fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>>
    fun getAllBooksByCategory(): Flow<ResultState<List<BookModel>>>


    fun onCancelled(error: DatabaseError)
    fun trySend(error: com.fire.bookbuddy.common.ResultState.Error<com.google.firebase.database.DatabaseException>)
}