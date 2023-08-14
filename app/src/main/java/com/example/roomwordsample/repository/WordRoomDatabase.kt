package com.example.roomwordsample.repository

import android.content.Context
import androidx.room.*
import com.example.roomwordsample.Utils.Utils.wordDatabaseKey
import com.example.roomwordsample.data.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    wordDatabaseKey
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}