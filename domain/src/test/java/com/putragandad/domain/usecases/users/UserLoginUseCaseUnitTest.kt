package com.putragandad.domain.usecases.users

import com.google.common.truth.Truth.assertThat
import com.putragandad.data.repository.FakeUserRepository
import com.putragandad.moviedbch5.domain.usecases.users.UserLoginUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UserRegisterUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UserLoginUseCaseUnitTest {
    private lateinit var userLoginUseCase: UserLoginUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        userLoginUseCase = UserLoginUseCase(fakeUserRepository)

        // insert fake user credentials
        runBlocking {
            fakeUserRepository.registerUser(
                "",
                "testemail",
                "testpassword"
            )
        }
    }

    @Test
    fun `test login with correct credentials`() {
        runBlocking {
            val email = "testemail"
            val password = "testpassword"
            userLoginUseCase.invoke(email, password)
            assertThat(fakeUserRepository.readLoginStatus.first()).isEqualTo(true)
        }
    }

    @Test
    fun `test login with wrong credentials`() {
        runBlocking {
            val email = "testemail"
            val password = "testpassword123"
            userLoginUseCase.invoke(email, password)
            assertThat(fakeUserRepository.readLoginStatus.first()).isEqualTo(false)
        }
    }
}