package com.example.roomwordsample.repository

import androidx.room.*
import com.example.roomwordsample.Utils.Utils.wordKey
import com.example.roomwordsample.Utils.Utils.wordTableKey
import com.example.roomwordsample.data.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM $wordTableKey ORDER BY $wordKey ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM $wordTableKey")
    suspend fun deleteAll()
}