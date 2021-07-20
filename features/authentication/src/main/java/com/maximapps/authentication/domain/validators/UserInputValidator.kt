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

import com.maximapps.authentication.R
import com.maximapps.authentication.domain.models.UserInput
import com.maximapps.authentication.domain.models.ValidationResult
import com.maximapps.authentication.domain.models.ValidatorResult

/**
 * Validates a user input.
 *
 * @since 0.2.1
 */
class UserInputValidator {
    private val validations = setOf(EmailValidation, PasswordValidation)

    fun validate(userInput: UserInput) = ValidatorResult(
        validations.filter { !it(userInput) }.map(::toValidationResult)
    )

    private fun toValidationResult(validation: Validation) = when (validation) {
        is EmailValidation ->
            ValidationResult.InvalidEmail(R.string.invalid_email)
        is PasswordValidation ->
            ValidationResult.InvalidPassword(R.string.invalid_password)
    }
}
