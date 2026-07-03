package com.example.chatbotapp

import CustomDialogViewHolder
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.dialogs.DialogsList
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import kotlinx.coroutines.launch
import java.util.UUID
import androidx.appcompat.app.AlertDialog

class DialogsActivity : AppCompatActivity() {

    private lateinit var dialogsList: DialogsList
    private lateinit var dialogsAdapter: DialogsListAdapter<Dialog>
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs)

        database = AppDatabase.getDatabase(this)
        dialogsList = findViewById(R.id.dialogsList)

        val imageLoader = ImageLoader { imageView, url, _ ->
            if (imageView != null && url != null) {
                Picasso.get().load(url).into(imageView)
            }
        }

        dialogsAdapter = DialogsListAdapter(R.layout.item_dialogs, CustomDialogViewHolder::class.java, imageLoader)
        dialogsList.setAdapter(dialogsAdapter)

        loadDialogsFromDatabase()

        dialogsAdapter.setOnDialogClickListener { dialog ->
            openChat(dialog)
        }
        dialogsList.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val childView = rv.findChildViewUnder(e.x, e.y)
                if (childView != null && e.action == MotionEvent.ACTION_UP) {
                    val holder = rv.getChildViewHolder(childView) as CustomDialogViewHolder
                    holder.setOnClickListener { dialog ->
                        openChat(dialog)
                    }
                }
                return false
            }
        })

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val dialog = (viewHolder as CustomDialogViewHolder).dialog
                AlertDialog.Builder(this@DialogsActivity)
                    .setTitle("Delete Conversation")
                    .setMessage("Are you sure you want to delete this conversation?")
                    .setPositiveButton("Yes") { _, _ ->
                        deleteDialog(dialog)
                    }
                    .setNegativeButton("No") { _, _ ->
                        dialogsAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        })
        itemTouchHelper.attachToRecyclerView(dialogsList)

        findViewById<FloatingActionButton>(R.id.fabNewConversation).setOnClickListener {
            startNewConversation()
        }
    }

    override fun onResume() {
        super.onResume()
        loadDialogsFromDatabase()
    }

    private fun loadDialogsFromDatabase() {
        lifecycleScope.launch {
            val dialogEntities = database.dialogDao().getAllDialogs()
            val dialogs = dialogEntities.map { entity ->
                Dialog(entity.id, entity.name, entity.lastMessage, entity.date)
            }
            dialogsAdapter.setItems(dialogs)
        }
    }

    private fun openChat(dialog: Dialog) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DIALOG_ID", dialog.getId())
        startActivity(intent)
    }

    private fun startNewConversation() {
        val newDialogId = UUID.randomUUID().toString()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DIALOG_ID", newDialogId)
        startActivity(intent)
    }

    private fun deleteDialog(dialog: Dialog) {
        lifecycleScope.launch {
            database.dialogDao().deleteDialog(dialog.getId())
            database.messageDao().deleteMessagesByDialogId(dialog.getId())
            loadDialogsFromDatabase()
        }
    }
}
