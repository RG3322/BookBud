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

/**
 * Implementation of [AllBookRepo] that interacts with Firebase Realtime Database.
 * This class provides methods to fetch all books, all categories, and books by category
 * from the Firebase Realtime Database.
 *
 * It uses Kotlin Flows and [callbackFlow] to handle asynchronous data retrieval
 * and emit [ResultState] objects to represent the loading, success, or error states.
 *
 * @property firebaseDatabase Instance of [FirebaseDatabase] used for accessing the database.
 *                            This is injected via Hilt.
 */
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
        firebaseDatabase.reference.child("BooksCategory").addValueEventListener(valueEvent)
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
        trySend(ResultState.Error(error.toException()))

        firebaseDatabase.reference.child("Books")

    }




    override fun trySend(error: ResultState.Error<DatabaseException>) {
        TODO("Not yet implemented")
    }

}

/**
 * DOCUMENTATION BY GPT
 *
 *
 * Perfect, let’s break this code down step by step in **super simple terms** — as if you’re new to Kotlin, Android, and Firebase.
 *
 * ---
 *
 * ### 🚀 What does this file do?
 *
 * This class (`AllBookRepoImpl`) is in charge of **talking to Firebase Realtime Database** and getting data about books and categories.
 *
 * Think of it as the **middleman** between Firebase and the rest of your app.
 *
 * * If your app needs **all books** → it asks this class.
 * * If your app needs **categories** → it asks this class.
 * * If your app needs **books filtered by category** → it asks this class.
 *
 * ---
 *
 * ### 📂 The structure
 *
 * ```kotlin
 * class AllBookRepoImpl @Inject constructor(
 *     val firebaseDatabase: FirebaseDatabase
 * ) : AllBookRepo
 * ```
 *
 * * `AllBookRepoImpl` → means “Implementation of the AllBookRepo interface.”
 * * `@Inject` → lets **Hilt** (dependency injection library) give you a `FirebaseDatabase` instance automatically. You don’t create it manually.
 * * `: AllBookRepo` → this means the class **follows the rules defined in the `AllBookRepo` interface** (so it must implement the functions declared there).
 *
 * ---
 *
 * ### 📦 The return type: `Flow<ResultState<T>>`
 *
 * All functions return a `Flow<ResultState<T>>`.
 *
 * * **Flow** = a Kotlin way of sending values **over time** (like a stream of updates).
 * * **ResultState** = a sealed class that wraps 3 possible states:
 *
 *   1. `Loading` → when data is being fetched.
 *   2. `Success` → when data comes successfully.
 *   3. `Error` → if something goes wrong.
 *
 * So the UI can listen to this `Flow` and always know the current status.
 *
 * ---
 *
 * ### 🟢 Function 1: `getAllBooks()`
 *
 * ```kotlin
 * override fun getAllBooks(): Flow<ResultState<List<BookModel>>> = callbackFlow {
 *     trySend(ResultState.Loading) // tell UI we are loading
 *     val valueEvent = object : ValueEventListener {
 *         override fun onDataChange(snapshot: DataSnapshot) {
 *             // Convert Firebase snapshot into a list of BookModel
 *             val items = snapshot.children.mapNotNull {
 *                 it.getValue(BookModel::class.java)
 *             }
 *             trySend(ResultState.Success(items)) // send data to UI
 *         }
 *
 *         override fun onCancelled(error: DatabaseError) {
 *             trySend(ResultState.Error(error.toException())) // send error
 *         }
 *     }
 *     firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
 *
 *     // clean up when flow is closed
 *     awaitClose {
 *         firebaseDatabase.reference.removeEventListener(valueEvent)
 *     }
 * }
 * ```
 *
 * 👉 What happens here:
 *
 * 1. First, it tells the UI: **“Loading…”**
 * 2. It listens to the `"Books"` node in Firebase.
 * 3. When Firebase sends data:
 *
 *    * Convert it into a list of `BookModel` objects.
 *    * Send it as `Success`.
 * 4. If Firebase fails, send an `Error`.
 * 5. `awaitClose` → stop listening when no one needs data anymore (prevents memory leaks).
 *
 * ---
 *
 * ### 🟡 Function 2: `getAllCategory()`
 *
 * Almost the same as `getAllBooks()`, but it looks at `"BooksCategory"` instead.
 *
 * 👉 Returns a list of `BookCategoryModel`.
 *
 * ---
 *
 * ### 🔵 Function 3: `getAllBooksByCategory()`
 *
 * ```kotlin
 * override fun getAllBooksByCategory(): Flow<ResultState<List<BookModel>>> = callbackFlow {
 *     ...
 *     firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
 *     ...
 * }
 * ```
 *
 * 👉 Right now, it’s basically **copy-paste of getAllBooks()** — looks at `"Books"` node and returns all books.
 *
 * ⚠️ It seems unfinished: ideally, it should filter by category.
 *
 * ---
 *
 * ### ❌ The `onCancelled()` at the bottom
 *
 * ```kotlin
 * override fun onCancelled(error: DatabaseError) {
 *     trySend(ResultState.Error(error.toException()))
 *     firebaseDatabase.reference.child("Books")
 * }
 * ```
 *
 * 👉 This looks **strange**:
 *
 * * `onCancelled` normally belongs to `ValueEventListener`, not here.
 * * This override doesn’t make much sense in a repo class — maybe it’s leftover code and should be removed.
 *
 * ---
 *
 * ### ❌ The `trySend()` at the bottom
 *
 * ```kotlin
 * override fun trySend(error: ResultState.Error<DatabaseException>) {
 *     TODO("Not yet implemented")
 * }
 * ```
 *
 * 👉 This also looks wrong.
 *
 * * `trySend` is already provided by `callbackFlow`, so you don’t need to override it yourself.
 * * This is probably an accidental implementation.
 *
 * ---
 *
 * ### ✅ In summary (for newbies):
 *
 * * This class talks to **Firebase Realtime Database**.
 * * It has functions to get books and categories.
 * * It uses **Flow** so your UI can react to updates in real-time (loading, success, error).
 * * **callbackFlow** helps convert Firebase callbacks into Flows.
 * * Some extra code at the bottom looks like **mistakes / not needed**.
 *
 * ---
 *
 * Would you like me to **clean this code up** (remove the confusing unused overrides, and make `getAllBooksByCategory()` actually filter by category) so you have a “correct and simple” version?
 *
 */

