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

package com.maximapps.authentication.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.maximapps.authentication.data.models.AuthenticationResult
import com.maximapps.authentication.domain.models.UserInput
import com.maximapps.core.domain.preferences.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

/**
 * Repository for user authentication operations.
 *
 * @since 0.2.1
 */
class AuthenticationRepository(
    private val firebaseAuth: FirebaseAuth,
    private val userPreferences: UserPreferences
) {

    /**
     * Returns whether there is an authenticated user in the user preferences.
     *
     * @since 0.2.1
     */
    fun isUserAuthenticated() = userPreferences.isAuthenticated

    /**
     * Signs a user in with an email and password.
     *
     * @since 0.2.1
     */
    fun login(userInput: UserInput) = flow {
        emit(AuthenticationResult.Loading)
        val result = firebaseAuth
            .signInWithEmailAndPassword(userInput.email, userInput.password)
            .await()

        result.user?.let { userPreferences.storeLoggedInUserDetails(it.email) }
        emit(AuthenticationResult.Success(result))

    }.catch { emit(AuthenticationResult.Failed(it.message)) }.flowOn(Dispatchers.IO)

    /**
     * Register a user with an email and password.
     *
     * @since 0.2.1
     */
    fun register(userInput: UserInput) = flow {
        emit(AuthenticationResult.Loading)
        val result = firebaseAuth
            .createUserWithEmailAndPassword(userInput.email, userInput.password)
            .await()

        result.user?.let { userPreferences.storeLoggedInUserDetails(it.email) }
        emit(AuthenticationResult.Success(result))
    }.catch { emit(AuthenticationResult.Failed(it.message)) }.flowOn(Dispatchers.IO)
}
