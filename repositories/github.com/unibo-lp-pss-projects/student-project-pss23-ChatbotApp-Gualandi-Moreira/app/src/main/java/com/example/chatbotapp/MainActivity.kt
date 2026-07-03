package com.example.chatbotapp

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.android.volley.AuthFailureError
import com.android.volley.NetworkError
import com.android.volley.ServerError
import com.android.volley.TimeoutError
import com.example.myapplication.Message
import com.example.myapplication.User
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesList
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var chatbotTextApi: ChatbotTextApi
    private lateinit var chatbotImageApi: ChatbotImageApi
    private lateinit var editTextText: TextInputEditText
    private lateinit var sendButton: MaterialButton
    private lateinit var messagesList: MessagesList
    private lateinit var us: User
    private lateinit var bot: User
    private lateinit var adapter: MessagesListAdapter<Message>
    private lateinit var database: AppDatabase
    private var currentDialogId: String? = null
    private var selectedModel: String = "gpt-3.5-turbo" // Default model



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        // Manually configure edge-to-edge display
        //
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = selectedModel
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                if (imeInsets.bottom > systemBars.bottom) imeInsets.bottom else systemBars.bottom
            )

            insets
        }

        currentDialogId = intent.getStringExtra("DIALOG_ID")

        chatbotTextApi = ChatbotTextApi(this)
        chatbotImageApi = ChatbotImageApi(this)
        editTextText = findViewById(R.id.editTextText)
        sendButton = findViewById(R.id.sendButton)
        messagesList = findViewById(R.id.messagesList)

        val imageLoader: ImageLoader =
            ImageLoader { imageView, url, _ -> Picasso.get().load(url).into(imageView) }
        adapter = MessagesListAdapter<Message>("1", imageLoader)
        messagesList.setAdapter(adapter)

        us = User("1", "User", "")
        bot = User("2", "Bot", "")
        database = AppDatabase.getDatabase(this)

        lifecycleScope.launch {
            if (currentDialogId != null) {
                val existingDialog = database.dialogDao().getDialogById(currentDialogId!!)
                if (existingDialog == null) {
                    // This is a new conversation
                    val newDialog = DialogEntity(
                        id = currentDialogId!!,
                        name = "New Conversation",
                        lastMessage = "",
                        date = Date()
                    )
                    database.dialogDao().insertOrUpdateDialog(newDialog)
                }
                // If existingDialog is not null, we don't need to do anything
                // as we're opening an existing conversation
            }
        }

        sendButton.setOnClickListener {
            val inputText = editTextText.text.toString().trim()
            val message = Message("1", inputText, us, Calendar.getInstance().time, "")
            adapter.addToStart(message, true)
            Log.d("msg is", message.text)
            saveMessageToDatabase(message)
            handleUserInput(inputText)
            clearInput()
        }

        loadMessagesFromDatabase()

    }

    private fun saveMessageToDatabase(message: Message) {
        val messageEntity = MessageEntity(
            userId = message.user.id,
            text = message.text,
            date = message.createdAt,
            imageUrl = message.imageUrl,
            dialogId = currentDialogId ?: "default"
        )
        lifecycleScope.launch {
            database.messageDao().insertMessage(messageEntity)
            saveOrUpdateDialog(message)
        }
    }

    private fun saveOrUpdateDialog(message: Message) {
        val dialogId = currentDialogId ?: "default"
        val dialogName = "Chatbot"
        val lastMessage = message.text
        val date = message.createdAt

        val dialogEntity = DialogEntity(
            id = dialogId,
            name = dialogName,
            lastMessage = lastMessage,
            date = date
        )
        lifecycleScope.launch {
            database.dialogDao().insertOrUpdateDialog(dialogEntity)
        }
    }

    private fun loadMessagesFromDatabase() {
        lifecycleScope.launch {
            val messages = if (currentDialogId != null) {
                database.messageDao().getMessagesByDialogId(currentDialogId!!)
            } else {
                database.messageDao().getAllMessages()
            }
            val messageList = messages.map { messageEntity ->
                val user = if (messageEntity.userId == us.id) us else bot
                Message(
                    mId = messageEntity.id.toString(),
                    mText = messageEntity.text,
                    mUser = user,
                    mDate = messageEntity.date,
                    mUrl = messageEntity.imageUrl
                )
            }
            val reversedMessageList = messageList.reversed()
            adapter.addToEnd(reversedMessageList, true)
        }
    }

    private fun handleUserInput(inputText: String) {
        if (inputText.startsWith("Generate image:")) {
            val imagePrompt = inputText.removePrefix("Generate image:").trim()
            handleImageGeneration(imagePrompt)
        } else {
            handleTextGeneration(inputText)
        }
    }

    private fun handleImageGeneration(imagePrompt: String) {
        chatbotImageApi.generateImage(imagePrompt) { imageUrl, error ->
            if (error != null) {
                // Handle error
                val errorMessage = Message(
                    "2",
                    "Failed to generate image: ${error.message}",
                    bot,
                    Calendar.getInstance().time,
                    ""
                )
                runOnUiThread {
                    adapter.addToStart(errorMessage, true)
                    saveMessageToDatabase(errorMessage)
                }
            } else {
                // Handle display image URL
                val imageMessage =
                    Message("2", "Image:", bot, Calendar.getInstance().time, imageUrl)
                runOnUiThread {
                    adapter.addToStart(imageMessage, true)
                    saveMessageToDatabase(imageMessage)
                }
            }
        }
    }

    private fun handleTextGeneration(inputText: String) {
        lifecycleScope.launch {
            currentDialogId?.let { dialogId ->
                val previousMessages = withContext(Dispatchers.IO) {
                    database.messageDao().getMessagesByDialogId(dialogId)
                }.map { messageEntity ->
                    val user = if (messageEntity.userId == us.id) us else bot
                    Message(
                        mId = messageEntity.id.toString(),
                        mText = messageEntity.text,
                        mUser = user,
                        mDate = messageEntity.date,
                        mUrl = messageEntity.imageUrl
                    )
                }

                chatbotTextApi.generateText(inputText, previousMessages, selectedModel) { response, error ->
                    if (error != null) {
                        val errorMessage = Message(
                            "2",
                            "Error: ${error.message}",
                            bot,
                            Calendar.getInstance().time,
                            ""
                        )
                        adapter.addToStart(errorMessage, true)
                        saveMessageToDatabase(errorMessage)
                    } else {
                        val botResponse = Message(
                            "2",
                            response?.answer ?: "Sorry, I didn't get that.",
                            bot,
                            Calendar.getInstance().time,
                            ""
                        )
                        adapter.addToStart(botResponse, true)
                        saveMessageToDatabase(botResponse)
                    }
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.model_gpt_3_5 -> {
                selectedModel = "gpt-3.5-turbo"
                Toast.makeText(this, "Selected GPT-3", Toast.LENGTH_SHORT).show()
                supportActionBar?.title = selectedModel
                true
            }
            R.id.model_gpt_4t -> {
                selectedModel = "gpt-4-turbo"
                Toast.makeText(this, "Selected GPT-3.5", Toast.LENGTH_SHORT).show()
                supportActionBar?.title = selectedModel
                true
            }
            R.id.model_gpt_4o -> {
                selectedModel = "gpt-4o"
                Toast.makeText(this, "Selected GPT-4", Toast.LENGTH_SHORT).show()
                supportActionBar?.title = selectedModel
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun handleApiError(errorMessage: String) {
        val errorMsg = Message(
            "2",
            errorMessage,
            bot,
            Calendar.getInstance().time,
            ""
        )
        adapter.addToStart(errorMsg, true)
        saveMessageToDatabase(errorMsg)
    }

    private fun clearInput() {
        editTextText.text?.clear()
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editTextText.windowToken, 0)
    }

    private fun getErrorMessage(error: Exception): String {
        return when (error) {
            is AuthFailureError -> getString(R.string.error_authentication_failed, error.message)
            is NetworkError -> getString(R.string.error_network, error.message)
            is TimeoutError -> getString(R.string.error_timeout)
            is ServerError -> getString(R.string.error_server)
            else -> getString(R.string.error_unknown, error.message)
        }
    }
}
