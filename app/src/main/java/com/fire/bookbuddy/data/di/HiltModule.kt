package com.fire.bookbuddy.data.di

// Import the AllBookRepoImpl class, which is likely an implementation of a repository interface for book data.
import com.fire.bookbuddy.RepoImpl.AllBookRepoImpl
// Import the FirebaseDatabase class for interacting with Firebase Realtime Database.
import com.google.firebase.database.FirebaseDatabase
// Import the FirebaseStorage class for interacting with Firebase Storage.
import com.google.firebase.storage.FirebaseStorage
// Import the Module annotation from Dagger Hilt, indicating this is a Hilt module.
import dagger.Module
// Import the Provides annotation from Dagger Hilt, used to mark methods that provide dependencies.
import dagger.Provides
// Import the InstallIn annotation from Dagger Hilt, specifying the Hilt component this module is installed in.
import dagger.hilt.InstallIn
// Import the SingletonComponent class from Dagger Hilt, representing a singleton-scoped component.
import dagger.hilt.components.SingletonComponent
// Import the Singleton annotation from javax.inject, indicating that the provided dependency should be a singleton.
import javax.inject.Singleton

// The @Module annotation marks this object as a Dagger Hilt module.
// Modules are responsible for providing instances of dependencies.
@Module
// The @InstallIn(SingletonComponent::class) annotation specifies that this module's bindings
// will be available in the SingletonComponent. This means the provided dependencies will have a
// singleton scope, i.e., only one instance will be created and shared throughout the application's lifecycle.
@InstallIn(SingletonComponent::class)
object HiltModule {
    // The @Provides annotation marks this function as a provider method.
    // Hilt will call this method when an instance of FirebaseDatabase is needed.
    @Provides
    // The @Singleton annotation ensures that only one instance of FirebaseDatabase is created
    // and reused throughout the application.
    @Singleton
    fun provideRealtimeDatabase() : FirebaseDatabase {
        // Returns an instance of FirebaseDatabase using the getInstance() method.
        return FirebaseDatabase.getInstance()
    }
    // The @Provides annotation marks this function as a provider method.
    // Hilt will call this method when an instance of FirebaseStorage is needed.
    @Provides
    // The @Singleton annotation ensures that only one instance of FirebaseStorage is created
    // and reused throughout the application.
    @Singleton
    fun provideFirebaseStorage() : FirebaseStorage{
        // Returns an instance of FirebaseStorage using the getInstance() method.
        return FirebaseStorage.getInstance()
    }



    // The @Provides annotation marks this function as a provider method.
    // Hilt will call this method when an instance of AllBookRepoImpl is needed.
    @Provides
    // The @Singleton annotation ensures that only one instance of AllBookRepoImpl is created
    // and reused throughout the application.
    @Singleton
    fun   provideAllBookRepo(firebaseDatabase: FirebaseDatabase): AllBookRepoImpl {
        // Creates and returns an instance of AllBookRepoImpl.
        // It takes a FirebaseDatabase instance as a constructor argument, which Hilt will
        // automatically provide because there's a @Provides method (provideRealtimeDatabase) for it.
        return AllBookRepoImpl (firebaseDatabase)

    }

}