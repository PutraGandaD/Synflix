package com.putragandad.domain.usecases.users

import com.google.common.truth.Truth.assertThat
import com.putragandad.data.repository.FakeUserRepository
import com.putragandad.moviedbch5.domain.usecases.users.UserRegisterUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserRegisterUseCaseTest {
    private lateinit var userRegisterUseCase: UserRegisterUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        userRegisterUseCase = UserRegisterUseCase(fakeUserRepository)
    }

    @Test
    fun `register user and get registered user info`() {
        runBlocking {
            val fullname = "testfullname"
            val email = "testemail"
            val password = "testpassword"
            userRegisterUseCase.invoke(
                fullname, email, password
            )
            assertThat(fakeUserRepository.readAccountUserFullName.first()).isEqualTo(fullname)
            assertThat(fakeUserRepository.readAccountEmail.first()).isEqualTo(email)
            assertThat(fakeUserRepository.readAccountPassword.first()).isEqualTo(password)
        }
    }
}