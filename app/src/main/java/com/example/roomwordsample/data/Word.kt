package com.example.roomwordsample.data

import androidx.room.*
import com.example.roomwordsample.Utils.Utils.wordKey
import com.example.roomwordsample.Utils.Utils.wordTableKey

@Entity(tableName = wordTableKey)
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = wordKey) val word: String
)