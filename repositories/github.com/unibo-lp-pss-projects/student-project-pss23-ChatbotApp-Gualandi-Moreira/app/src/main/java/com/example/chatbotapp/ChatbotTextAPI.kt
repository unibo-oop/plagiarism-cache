package com.example.chatbotapp

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Message
import org.json.JSONArray
import org.json.JSONObject

open class ChatbotTextApi(private val context: Context) {

    companion object {
        const val API_URL = "https://api.openai.com/v1/chat/completions"
        private const val MODEL = "gpt-3.5-turbo"
        private const val TEMPERATURE = 0
        const val TOKEN = ""
    }

    protected open val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    open fun generateText(input: String, previousMessages: List<Message>, model: String, callback: (ChatbotTextResponse?, Exception?) -> Unit) {
        val messagesArray = JSONArray()

        // Add previous messages to the JSON array
        for (message in previousMessages) {
            val role = if (message.user.id == "1") "user" else "assistant"
            messagesArray.put(JSONObject().apply {
                put("role", role)
                put("content", message.text)
            })
        }

        // Add the current user input to the JSON array
        messagesArray.put(JSONObject().apply {
            put("role", "user")
            put("content", input)
        })

        val jsonBody = JSONObject().apply {
            put("model", model)
            put("messages", messagesArray)
            put("temperature", TEMPERATURE)
        }

        val request = object : JsonObjectRequest(
            Request.Method.POST,
            API_URL,
            jsonBody,
            Response.Listener { response ->
                try {
                    val answer = response.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content")
                    val chatbotResponse = ChatbotTextResponse(answer)
                    callback(chatbotResponse, null)
                } catch (e: Exception) {
                    callback(null, e)
                }
            },
            Response.ErrorListener { error ->
                callback(null, error)
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                return HashMap<String, String>().apply {
                    put("Content-Type", "application/json")
                    put("Authorization", "Bearer $TOKEN")
                }
            }

            override fun getRetryPolicy(): DefaultRetryPolicy {
                return DefaultRetryPolicy(
                    40000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
            }
        }

        requestQueue.add(request)
    }

}

data class ChatbotTextResponse(val answer: String)
