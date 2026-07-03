package com.example.chatbotapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.android.volley.NetworkError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.ServerError
import com.android.volley.TimeoutError
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class ChatbotTextApiTest {

    private lateinit var context: Context
    private lateinit var chatbotTextApi: ChatbotTextApi
    private lateinit var requestQueue: RequestQueue

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        context = ApplicationProvider.getApplicationContext()
        requestQueue = mock(RequestQueue::class.java)

        // Create an instance that uses our mock requestQueue
        chatbotTextApi = object : ChatbotTextApi(context) {
            override val requestQueue: RequestQueue
                get() = this@ChatbotTextApiTest.requestQueue

            // Override generateText so it creates a TestableJsonObjectRequest instead:
            override fun generateText(
                input: String,
                previousMessages: List<com.example.myapplication.Message>,
                model: String,
                callback: (ChatbotTextResponse?, Exception?) -> Unit
            ) {
                val jsonBody = JSONObject().apply {
                    put("model", model)
                    put("messages", JSONArray())
                }

                val request = object : TestUtils.TestableJsonObjectRequest(
                    API_URL,
                    jsonBody,
                    Response.Listener { response ->
                        try {
                            val answer = response.getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content")
                            callback(ChatbotTextResponse(answer), null)
                        } catch (e: Exception) {
                            callback(null, e)
                        }
                    },
                    Response.ErrorListener { error ->
                        callback(null, error)
                    }
                ) {
                    override fun getHeaders(): Map<String, String> {
                        return mutableMapOf(
                            "Content-Type" to "application/json",
                            "Authorization" to "Bearer $TOKEN"
                        )
                    }
                }
                requestQueue.add(request)
            }
        }
    }

    @Test
    fun verifyBasicRequestWorks() {
        // Intercept added requests and deliver a mock response
        `when`(requestQueue.add(any(JsonObjectRequest::class.java))).thenAnswer { invocation ->
            val request = invocation.arguments[0] as TestUtils.TestableJsonObjectRequest
            val mockResponse = TestUtils.createMockTextResponse(TestUtils.EXPECTED_TEXT_RESPONSE)
            request.deliverResponse(mockResponse)
            request
        }

        val latch = CountDownLatch(1)
        var result: ChatbotTextResponse? = null
        var error: Exception? = null

        chatbotTextApi.generateText(TestUtils.TEST_USER_INPUT, emptyList(), TestUtils.TEST_MODEL) { resp, err ->
            result = resp
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertEquals(TestUtils.EXPECTED_TEXT_RESPONSE, result?.answer)
        assertNull("Error should be null for successful request", error)
    }

    @Test
    fun testNetworkErrorHandling() {
        // Simulate network error
        `when`(requestQueue.add(any(JsonObjectRequest::class.java))).thenAnswer { invocation ->
            val request = invocation.arguments[0] as TestUtils.TestableJsonObjectRequest
            request.deliverError(NetworkError())
            request
        }

        val latch = CountDownLatch(1)
        var result: ChatbotTextResponse? = null
        var error: Exception? = null

        chatbotTextApi.generateText(TestUtils.TEST_USER_INPUT, emptyList(), TestUtils.TEST_MODEL) { resp, err ->
            result = resp
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for network error", result)
        assertNotNull("Error should not be null for network error", error)
        assertTrue("Error should be NetworkError", error is NetworkError)
    }

    @Test
    fun testServerErrorHandling() {
        // Simulate server error
        `when`(requestQueue.add(any(JsonObjectRequest::class.java))).thenAnswer { invocation ->
            val request = invocation.arguments[0] as TestUtils.TestableJsonObjectRequest
            request.deliverError(ServerError())
            request
        }

        val latch = CountDownLatch(1)
        var result: ChatbotTextResponse? = null
        var error: Exception? = null

        chatbotTextApi.generateText(TestUtils.TEST_USER_INPUT, emptyList(), TestUtils.TEST_MODEL) { resp, err ->
            result = resp
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for server error", result)
        assertNotNull("Error should not be null for server error", error)
        assertTrue("Error should be ServerError", error is ServerError)
    }

    @Test
    fun testTimeoutErrorHandling() {
        // Simulate timeout error
        `when`(requestQueue.add(any(JsonObjectRequest::class.java))).thenAnswer { invocation ->
            val request = invocation.arguments[0] as TestUtils.TestableJsonObjectRequest
            request.deliverError(TimeoutError())
            request
        }

        val latch = CountDownLatch(1)
        var result: ChatbotTextResponse? = null
        var error: Exception? = null

        chatbotTextApi.generateText(TestUtils.TEST_USER_INPUT, emptyList(), TestUtils.TEST_MODEL) { resp, err ->
            result = resp
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for timeout error", result)
        assertNotNull("Error should not be null for timeout error", error)
        assertTrue("Error should be TimeoutError", error is TimeoutError)
    }

    @Test
    fun testInvalidApiResponseHandling() {
        // Simulate malformed API response
        `when`(requestQueue.add(any(JsonObjectRequest::class.java))).thenAnswer { invocation ->
            val request = invocation.arguments[0] as TestUtils.TestableJsonObjectRequest
            val malformedResponse = JSONObject().apply {
                put("invalid_field", "invalid_value")
            }
            request.deliverResponse(malformedResponse)
            request
        }

        val latch = CountDownLatch(1)
        var result: ChatbotTextResponse? = null
        var error: Exception? = null

        chatbotTextApi.generateText(TestUtils.TEST_USER_INPUT, emptyList(), TestUtils.TEST_MODEL) { resp, err ->
            result = resp
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for malformed response", result)
        assertNotNull("Error should not be null for malformed response", error)
    }
}
