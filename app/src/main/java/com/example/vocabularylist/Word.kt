package com.example.vocabularylist

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "word")
data class Word(
    val voca: String,
    val mean: String,
    val type: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    )
    : Parcelable