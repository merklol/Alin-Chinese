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

import android.content.Context
import com.maximapps.core.domain.preferences.UserPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Default implementation of the UserPreferences interface.
 *
 * @since 0.1
 */
class DefaultUserPreferences @Inject constructor(
    @ApplicationContext context: Context
) : UserPreferences {
    private val preferences = context
        .getSharedPreferences("user_info", Context.MODE_PRIVATE)

    override val email
        get() = preferences.getString("user_email", "Unknown User")

    override val isAuthenticated
        get() = preferences.getBoolean("is_authenticated", false)

    override fun setAuthenticationState(state: Boolean) = preferences.edit()
        .putBoolean("is_authenticated", state)
        .apply()

    override fun storeLoggedInUserDetails(email: String?) = preferences
        .edit()
        .putString("user_email", email)
        .putBoolean("is_authenticated", true)
        .apply()
}
