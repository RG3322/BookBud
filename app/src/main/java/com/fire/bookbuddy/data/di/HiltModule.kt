package com.fire.bookbuddy.data.di

import com.fire.bookbuddy.RepoImpl.AllBookRepoImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideRealtimeDatabase() : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
    @Provides
    @Singleton
    fun provideFirebaseStorage() : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun   provideAllBookRepo(firebaseDatabase: FirebaseDatabase): AllBookRepoImpl {
        return AllBookRepoImpl (firebaseDatabase)

    }

}