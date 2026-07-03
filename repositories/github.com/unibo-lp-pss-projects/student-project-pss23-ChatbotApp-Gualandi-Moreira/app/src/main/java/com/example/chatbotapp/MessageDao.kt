package com.example.chatbotapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {
    @Insert
    suspend fun insertMessage(message: MessageEntity)

    @Query("SELECT * FROM messages ORDER BY date DESC")
    suspend fun getAllMessages(): List<MessageEntity>

    @Query("SELECT * FROM messages WHERE dialogId = :dialogId ORDER BY date DESC")
    suspend fun getMessagesByDialogId(dialogId: String): List<MessageEntity>

    @Query("DELETE FROM messages WHERE dialogId = :dialogId")
    suspend fun deleteMessagesByDialogId(dialogId: String)


}
