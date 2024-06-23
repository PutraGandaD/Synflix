package com.putragandad.testdomain.usecases.movies

import com.google.common.truth.Truth.assertThat
import com.putragandad.common.utils.Resource
import com.putragandad.data.network.ApiService
import com.putragandad.domain.usecases.movies.DetailsUseCase
import com.putragandad.testdomain.data.repository.FakeMovieRepository
import com.putragandad.testdomain.utils.RetrofitBuilder
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class DetailsUseCaseUnitTest {
    private lateinit var mockWebServer: MockWebServer // initiate MockWebServer
    private val apiService: ApiService by lazy {  // initiate Retrofit
        RetrofitBuilder.generateRetrofit(mockWebServer).create(ApiService::class.java)
    }
    private lateinit var fakeMovieRepository: FakeMovieRepository
    private lateinit var detailsUseCase: DetailsUseCase

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        fakeMovieRepository = FakeMovieRepository(apiService)
        detailsUseCase = DetailsUseCase(fakeMovieRepository)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get movie details based on id and retrieve the exact movie`() {
        runBlocking {
            val filePath = File("src/test/resources/MovieDetailsJsonSample.json").readText()
            val mockResponse = MockResponse() // simulate api with response code 200 and has json return
                .setBody(filePath)
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json")

            mockWebServer.enqueue(mockResponse)

            val id = "653346"
            val results = detailsUseCase.invoke(id).last()

            assertThat(results.data?.id).isEqualTo(id.toInt())
            assertThat(results).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun `HTTP Exception Test = emit error state and get empty `() {
        runBlocking {
            val mockResponse = MockResponse() // simulate where api not returning any result/json and return error code 404
                .setResponseCode(400)
            mockWebServer.enqueue(mockResponse)

            val results = detailsUseCase.invoke("653346").last()
            assertThat(results).isInstanceOf(Resource.Error::class.java)
            assertThat(results.message).isEqualTo("An unexpected error occured (Client Error)")
        }
    }

    @Test
    fun `IOException Test = emit error state and get empty list of movie`() {
        runBlocking {
            val mockResponse = MockResponse() // simulate where api call is disconnected in the middle and get corrupted data / nothing at all
                .setResponseCode(200)
                .setSocketPolicy(SocketPolicy.DISCONNECT_DURING_RESPONSE_BODY)
                .setBody("{}")
            mockWebServer.enqueue(mockResponse)

            val results = detailsUseCase.invoke("653346").last()
            assertThat(results).isInstanceOf(Resource.Error::class.java)
            assertThat(results.message).isEqualTo("Couldn't reach server. Check your internet connection.")
        }
    }

}