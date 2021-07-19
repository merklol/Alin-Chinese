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

package com.maximapps.core.data.preferences

import javax.inject.Inject

/**
 * Wrapper class for SharedPreferences.
 *
 * @since 0.1
 */
interface UserPreferences {

    /**
     * Retrieves an email from the preferences.
     *
     * @since 0.1
     */
    val email: String?

    /**
     * Retrieves the current authentication state from the preferences.
     *
     * @since 0.1
     */
    val isAuthenticated: Boolean

    /**
     * Set a new authentication state from the preferences.
     *
     * @param state authentication state in boolean
     * @since 0.1
     */
    fun setAuthenticationState(state: Boolean)

    /**
     * Saves logged in user details into the preferences.
     *
     * @param email an email address in String format
     * @since 0.1
     */
    fun storeLoggedInUserDetails(email: String?)

    /**
     * Fake implementation of the UserPreferences interface.
     *
     * @since 0.1
     */
    class Fake @Inject constructor(): UserPreferences {
        override val email = "admin@gmail.com"

        override val isAuthenticated = false

        override fun setAuthenticationState(state: Boolean) { /*no-op*/ }

        override fun storeLoggedInUserDetails(email: String?) { /*no-op*/ }
    }
}
