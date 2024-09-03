package com.putragandad.synflix.usecases.users

import com.google.common.truth.Truth
import com.putragandad.synflix.domain.usecases.users.UserRegisterUseCase
import com.putragandad.synflix.data.repository.FakeUserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserRegisterUseCaseUnitTest {
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
            Truth.assertThat(fakeUserRepository.readAccountUserFullName.first()).isEqualTo(fullname)
            Truth.assertThat(fakeUserRepository.readAccountEmail.first()).isEqualTo(email)
            Truth.assertThat(fakeUserRepository.readAccountPassword.first()).isEqualTo(password)
        }
    }
}