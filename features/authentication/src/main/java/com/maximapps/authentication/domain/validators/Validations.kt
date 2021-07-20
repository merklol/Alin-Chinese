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

package com.maximapps.authentication.domain.validators

import androidx.core.util.PatternsCompat
import com.maximapps.authentication.domain.models.UserInput
import java.util.regex.Pattern

/**
 * Base class for validations.
 *
 * @since 0.2.1
 */
internal sealed interface Validation {
    operator fun invoke(input: UserInput): Boolean
}

/**
 * Validates an email address.
 *
 * A valid email address should contain @
 *
 * @sample UserInputValidator.validate
 * @since 0.2.1
 */
internal object EmailValidation : Validation {
    override fun invoke(input: UserInput): Boolean = input.email.isNotEmpty()
            && PatternsCompat.EMAIL_ADDRESS.matcher(input.email).matches()
}

/**
 * Validates a password.
 *
 * A valid password should have at least 6 characters, 1 letter and 1 number.
 *
 * @sample UserInputValidator.validate
 * @since 0.2.1
 */
internal object PasswordValidation : Validation {
    override fun invoke(input: UserInput): Boolean = Pattern
        .compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")
        .matcher(input.password).matches() && input.password.isNotEmpty()
}
