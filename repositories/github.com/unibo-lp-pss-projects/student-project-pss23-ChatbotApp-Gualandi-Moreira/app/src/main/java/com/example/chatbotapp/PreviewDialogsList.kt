package com.example.chatbotapp

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class PreviewDialogsList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        // Do nothing in preview mode
    }
}
