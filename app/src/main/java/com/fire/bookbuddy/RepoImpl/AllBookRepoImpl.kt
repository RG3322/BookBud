package com.fire.bookbuddy.RepoImpl

import com.fire.bookbuddy.common.BookCategoryModel
import com.fire.bookbuddy.common.BookModel
import com.fire.bookbuddy.common.ResultState
import com.fire.bookbuddy.domain.repo.AllBookRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl @Inject constructor(val firebaseDatabase: FirebaseDatabase) : AllBookRepo {
    override fun getAllBooks(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.mapNotNull {
                    it.getValue(BookModel::class.java)
                }
                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)

        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
        }

    }


    override fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.mapNotNull {
                    it.getValue(BookCategoryModel::class.java)
                }
                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("Category").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
        }
    }

    override fun getAllBooksByCategory(): Flow<ResultState<List<BookModel>>> = callbackFlow{
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.mapNotNull {
                    it.getValue(BookModel::class.java)
                }
                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
        awaitClose{
            firebaseDatabase.reference.removeEventListener(valueEvent)
        }    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }

    override fun trySend(error: ResultState.Error<DatabaseException>) {
        TODO("Not yet implemented")
    }
}

