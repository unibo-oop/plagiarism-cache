package com.example.chatbotapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val text: String,
    val date: Date,
    val imageUrl: String?,
    val dialogId: String
)
