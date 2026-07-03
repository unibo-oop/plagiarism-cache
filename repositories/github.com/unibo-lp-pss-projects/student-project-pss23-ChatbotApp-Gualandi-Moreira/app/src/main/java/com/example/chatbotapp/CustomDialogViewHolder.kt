import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.chatbotapp.Dialog
import com.example.chatbotapp.R
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CustomDialogViewHolder(itemView: View) : DialogsListAdapter.DialogViewHolder<Dialog>(itemView) {
    private val avatarView: ImageView? = itemView.findViewById(R.id.dialogAvatar)
    private val nameView: TextView? = itemView.findViewById(R.id.dialogName)
    private val lastMessageView: TextView? = itemView.findViewById(R.id.dialogLastMessage)
    private val dateView: TextView? = itemView.findViewById(R.id.dialogDate)
    lateinit var dialog: Dialog

    override fun onBind(dialog: Dialog) {
        this.dialog = dialog
        nameView?.text = dialog.dialogName
        lastMessageView?.text = dialog.lastMessage.text
        dateView?.text = formatDate(dialog.lastMessage.createdAt ?: Date())
        avatarView?.visibility = View.GONE
    }
    fun formatDate(date: Date): String {
        val now = Calendar.getInstance()
        val then = Calendar.getInstance().apply { time = date }

        return when {
            now.get(Calendar.DATE) == then.get(Calendar.DATE) ->
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
            now.get(Calendar.YEAR) == then.get(Calendar.YEAR) ->
                SimpleDateFormat("dd MMM", Locale.getDefault()).format(date)
            else ->
                SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(date)
        }
    }
    fun setOnClickListener(listener: (Dialog) -> Unit) {
        itemView.setOnClickListener {
            listener(dialog)
        }
    }

}

