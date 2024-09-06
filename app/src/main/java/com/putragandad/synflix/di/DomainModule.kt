package com.putragandad.synflix.di

import com.putragandad.synflix.domain.usecases.movies.CastUseCase
import com.putragandad.synflix.domain.usecases.movies.DetailsUseCase
import com.putragandad.synflix.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.synflix.domain.usecases.movies.PopularUseCase
import com.putragandad.synflix.domain.usecases.movies.TopRatedUseCase
import com.putragandad.synflix.domain.usecases.users.CheckLoginUseCase
import com.putragandad.synflix.domain.usecases.users.ReadUserInfoUseCase
import com.putragandad.synflix.domain.usecases.users.SetProfilePictureUseCase
import com.putragandad.synflix.domain.usecases.users.UpdateUserInfoUseCase
import com.putragandad.synflix.domain.usecases.users.UserLoginUseCase
import com.putragandad.synflix.domain.usecases.users.UserLogoutUseCase
import com.putragandad.synflix.domain.usecases.users.UserRegisterUseCase
import org.koin.dsl.module

object DomainModule {
    val useCaseModule = module {
        factory { DetailsUseCase(get()) }
        factory { NowPlayingUseCase(get()) }
        factory { PopularUseCase(get()) }
        factory { TopRatedUseCase(get()) }
        factory { CastUseCase(get()) }

        factory { CheckLoginUseCase(get()) }
        factory { UserLoginUseCase(get()) }
        factory { UserRegisterUseCase(get()) }
        factory { ReadUserInfoUseCase(get()) }
        factory { UserLogoutUseCase(get()) }
        factory { UpdateUserInfoUseCase(get()) }
        factory { SetProfilePictureUseCase(get()) }
    }
}