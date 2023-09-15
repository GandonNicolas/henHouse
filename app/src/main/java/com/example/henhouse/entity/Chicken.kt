package com.example.henhouse.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Chicken(
    @ColumnInfo(name = "name") val chickenName: String,
    @ColumnInfo(name = "race") val race: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}