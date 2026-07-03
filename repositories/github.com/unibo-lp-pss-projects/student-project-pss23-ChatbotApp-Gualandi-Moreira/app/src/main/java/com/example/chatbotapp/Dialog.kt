package com.example.chatbotapp

import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.Date

class Dialog(
    private val id: String,
    private val name: String,
    private val lastMessage: String,
    private val date: Date,
    var isDeleteButtonVisible: Boolean = false
) : IDialog<IMessage> {

    override fun getId(): String = id

    override fun getDialogPhoto(): String? = null

    override fun getDialogName(): String = name

    override fun getUsers(): List<IUser> = emptyList()

    override fun getLastMessage(): IMessage {
        return object : IMessage {
            override fun getId(): String = "0"
            override fun getText(): String = lastMessage
            override fun getCreatedAt(): Date = date
            override fun getUser(): IUser = object : IUser {
                override fun getId(): String = "0"
                override fun getName(): String = "Bot"
                override fun getAvatar(): String? = null
            }
        }
    }

    override fun setLastMessage(message: IMessage?) {
        // Implement later
    }

    override fun getUnreadCount(): Int = 0
}
