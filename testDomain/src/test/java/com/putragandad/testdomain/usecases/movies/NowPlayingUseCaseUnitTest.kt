package com.putragandad.testdomain.usecases.movies

import com.google.common.truth.Truth.assertThat
import com.putragandad.moviedbch5.data.network.ApiService
import com.putragandad.moviedbch5.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.moviedbch5.utils.Resource
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

class NowPlayingUseCaseUnitTest {
    private lateinit var mockWebServer: MockWebServer // initiate MockWebServer
    private val apiService: ApiService by lazy {  // initiate Retrofit
        RetrofitBuilder.generateRetrofit(mockWebServer).create(ApiService::class.java)
    }
    private lateinit var fakeMovieRepository: FakeMovieRepository
    private lateinit var nowPlayingUseCase: NowPlayingUseCase

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        fakeMovieRepository = FakeMovieRepository(apiService)
        nowPlayingUseCase = NowPlayingUseCase(fakeMovieRepository)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get movie from api and emit success if results is not empty`() {
        runBlocking {
            val filePath = File("src/test/resources/NowPlayingMovieJsonSample.json").readText()
            val mockResponse = MockResponse() // simulate api with response code 200 and has json return
                .setBody(filePath)
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json")

            mockWebServer.enqueue(mockResponse)

            val results = nowPlayingUseCase.invoke().last()

            assertThat(results.data).isNotEmpty()
            assertThat(results).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun `HTTP Exception Test = emit error state and get empty list of movie`() {
        runBlocking {
            val mockResponse = MockResponse() // simulate where api not returning any result/json and return error code 404
                .setResponseCode(400)
            mockWebServer.enqueue(mockResponse)

            val results = nowPlayingUseCase.invoke().last()
            assertThat(results).isInstanceOf(Resource.Error::class.java)
            assertThat(results.message).isEqualTo("API Error. Data can't retrieved from the API")
            assertThat(results.data).isEmpty()
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

            val results = nowPlayingUseCase.invoke().last()
            assertThat(results).isInstanceOf(Resource.Error::class.java)
            assertThat(results.message).isEqualTo("Error. Connection Failure / Forced Disconnect / Data Corrupt")
            assertThat(results.data).isEmpty()
        }
    }
}