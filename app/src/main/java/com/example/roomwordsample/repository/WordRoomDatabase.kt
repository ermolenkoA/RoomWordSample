package com.example.roomwordsample.repository

import androidx.room.*
import com.example.roomwordsample.data.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}