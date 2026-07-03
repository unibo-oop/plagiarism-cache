package com.example.chatbotapp

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

/**
 * Utility class for testing that provides common test helpers and mock objects.
 */
object TestUtils {
    
    // Test constants
    const val TEST_TIMEOUT_SECONDS = 3L
    const val EXPECTED_TEXT_RESPONSE = "I'm doing well, thanks!"
    const val TEST_MODEL = "gpt-3.5-turbo"
    const val MOCK_IMAGE_URL = "https://example.com/generated-image.jpg"
    const val TEST_USER_INPUT = "Hello, how are you?"
    const val TEST_IMAGE_PROMPT = "A beautiful sunset over mountains"
    const val INVALID_PROMPT = "Invalid prompt"
    const val ERROR_MESSAGE = "Network error occurred"
    
    /**
     * Testable version of JsonObjectRequest that allows manual response delivery.
     * This eliminates code duplication between test classes.
     */
    open class TestableJsonObjectRequest(
        url: String,
        requestBody: JSONObject,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) : JsonObjectRequest(Method.POST, url, requestBody, listener, errorListener) {
        
        public override fun deliverResponse(response: JSONObject) {
            super.deliverResponse(response)
        }
        
        public override fun deliverError(error: com.android.volley.VolleyError) {
            super.deliverError(error)
        }
    }
    
    /**
     * Creates a mock successful text response from OpenAI API
     */
    fun createMockTextResponse(content: String): JSONObject {
        return JSONObject().apply {
            put("choices", org.json.JSONArray().put(
                JSONObject().put("message", 
                    JSONObject().put("content", content)
                )
            ))
        }
    }
    
    /**
     * Creates a mock successful image response from OpenAI API
     */
    fun createMockImageResponse(imageUrl: String): JSONObject {
        return JSONObject().apply {
            put("data", org.json.JSONArray().put(
                JSONObject().put("url", imageUrl)
            ))
        }
    }
    
    /**
     * Creates a mock error response
     */
    fun createMockErrorResponse(errorMessage: String): JSONObject {
        return JSONObject().apply {
            put("error", JSONObject().put("message", errorMessage))
        }
    }
}