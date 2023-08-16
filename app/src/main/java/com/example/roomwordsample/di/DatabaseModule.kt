package com.example.roomwordsample.di

import android.content.Context
import androidx.room.Room
import com.example.roomwordsample.Utils.Utils.wordTableKey
import com.example.roomwordsample.repository.WordDao
import com.example.roomwordsample.repository.WordRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideWordDao(database: WordRoomDatabase): WordDao{
        return database.wordDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): WordRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            WordRoomDatabase::class.java,
            wordTableKey
        ).build()
    }
}