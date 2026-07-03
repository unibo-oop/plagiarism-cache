package com.example.chatbotapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "dialogs")
data class DialogEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "last_message") val lastMessage: String,
    @ColumnInfo(name = "date") val date: Date
)
