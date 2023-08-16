package com.example.roomwordsample.repository

import com.example.roomwordsample.data.Word
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepository @Inject constructor(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun delete(word: String) {
        wordDao.delete(word)
    }
}