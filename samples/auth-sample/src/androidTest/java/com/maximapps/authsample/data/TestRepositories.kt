/*
 * MIT License
 *
 * Copyright (c) 2021 Maxim Smolyakov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.maximapps.authsample.data

import com.maximapps.authentication.data.models.AuthenticationResult
import com.maximapps.authentication.data.repositories.AuthenticationRepository
import com.maximapps.authentication.domain.models.UserInput
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.flow.flow

val userInput = UserInput("admin@gmail.com", "abc123")

fun provideRepositoryLoadingResult() = mockkClass(AuthenticationRepository::class).also {
    every { it.isUserAuthenticated() } returns false
    every { it.login(userInput) } answers {
        flow { emit(AuthenticationResult.Loading) }
    }
    every { it.register(userInput) } answers {
        flow { emit(AuthenticationResult.Loading) }
    }
}

fun provideRepositorySuccessResult() = mockkClass(AuthenticationRepository::class).also {
    every { it.isUserAuthenticated() } returns false
    every { it.login(userInput) } answers {
        flow { emit(AuthenticationResult.Success(TestAuthResult())) }
    }
    every { it.register(userInput) } answers {
        flow { emit(AuthenticationResult.Success(TestAuthResult())) }
    }
}

