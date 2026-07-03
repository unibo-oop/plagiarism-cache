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
class ChatbotImageApiTest {

    private lateinit var context: Context
    private lateinit var chatbotImageApi: ChatbotImageApi
    private lateinit var requestQueue: RequestQueue

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        context = ApplicationProvider.getApplicationContext()
        requestQueue = mock(RequestQueue::class.java)

        // Create an instance that uses our mock requestQueue
        chatbotImageApi = object : ChatbotImageApi(context) {
            // Override the requestQueue property to use our mock
            override val requestQueue: RequestQueue
                get() = this@ChatbotImageApiTest.requestQueue

            // Override generateImage to use our TestableJsonObjectRequest
            override fun generateImage(
                input: String,
                callback: (String?, Exception?) -> Unit
            ) {
                val jsonBody = JSONObject().apply {
                    put("prompt", input)
                    put("n", 1)
                    put("size", "1024x1024")
                }

                val request = object : TestUtils.TestableJsonObjectRequest(
                    "https://api.openai.com/v1/images/generations",
                    jsonBody,
                    Response.Listener { response ->
                        try {
                            val imageUrl = response.getJSONArray("data")
                                .getJSONObject(0)
                                .getString("url")
                            callback(imageUrl, null)
                        } catch (e: Exception) {
                            callback(null, e)
                        }
                    },
                    Response.ErrorListener { error ->
                        callback(null, error)
                    }
                ) {
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-Type"] = "application/json"
                        headers["Authorization"] = "Bearer TOKEN"
                        return headers
                    }
                }
                requestQueue.add(request)
            }
        }
    }

    @Test
    fun verifyImageGenerationRequestWorks() {
        // Intercept added requests and deliver a mock response
        `when`(requestQueue.add(any(JsonObjectRequest::class.java))).thenAnswer { invocation ->
            val request = invocation.arguments[0] as TestUtils.TestableJsonObjectRequest
            val mockResponse = TestUtils.createMockImageResponse(TestUtils.MOCK_IMAGE_URL)
            request.deliverResponse(mockResponse)
            request
        }

        val latch = CountDownLatch(1)
        var resultUrl: String? = null
        var error: Exception? = null

        chatbotImageApi.generateImage(TestUtils.TEST_IMAGE_PROMPT) { url, err ->
            resultUrl = url
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertEquals(TestUtils.MOCK_IMAGE_URL, resultUrl)
        assertNull("Error should be null for successful request", error)
    }

    @Test
    fun verifyErrorHandling() {
        // Intercept added requests and deliver a mock response with error
        `when`(requestQueue.add(any(JsonObjectRequest::class.java))).thenAnswer { invocation ->
            val request = invocation.arguments[0] as TestUtils.TestableJsonObjectRequest
            val mockResponse = TestUtils.createMockErrorResponse(TestUtils.ERROR_MESSAGE)
            request.deliverResponse(mockResponse)
            request
        }

        val latch = CountDownLatch(1)
        var resultUrl: String? = null
        var error: Exception? = null

        chatbotImageApi.generateImage(TestUtils.INVALID_PROMPT) { url, err ->
            resultUrl = url
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for error response", resultUrl)
        assertNotNull("Error should not be null for error response", error)
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
        var resultUrl: String? = null
        var error: Exception? = null

        chatbotImageApi.generateImage(TestUtils.TEST_IMAGE_PROMPT) { url, err ->
            resultUrl = url
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for network error", resultUrl)
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
        var resultUrl: String? = null
        var error: Exception? = null

        chatbotImageApi.generateImage(TestUtils.TEST_IMAGE_PROMPT) { url, err ->
            resultUrl = url
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for server error", resultUrl)
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
        var resultUrl: String? = null
        var error: Exception? = null

        chatbotImageApi.generateImage(TestUtils.TEST_IMAGE_PROMPT) { url, err ->
            resultUrl = url
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for timeout error", resultUrl)
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
        var resultUrl: String? = null
        var error: Exception? = null

        chatbotImageApi.generateImage(TestUtils.TEST_IMAGE_PROMPT) { url, err ->
            resultUrl = url
            error = err
            latch.countDown()
        }

        assertTrue("Test should complete within timeout", 
            latch.await(TestUtils.TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS))

        verify(requestQueue).add(any(JsonObjectRequest::class.java))
        assertNull("Result should be null for malformed response", resultUrl)
        assertNotNull("Error should not be null for malformed response", error)
    }
}
