package com.example.chatbotapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DialogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateDialog(dialog: DialogEntity)

    @Query("SELECT * FROM dialogs ORDER BY date DESC")
    suspend fun getAllDialogs(): List<DialogEntity>

    @Query("SELECT * FROM dialogs WHERE id = :dialogId")
    suspend fun getDialogById(dialogId: String): DialogEntity?

    @Query("DELETE FROM dialogs WHERE id = :dialogId")
    suspend fun deleteDialog(dialogId: String)
}