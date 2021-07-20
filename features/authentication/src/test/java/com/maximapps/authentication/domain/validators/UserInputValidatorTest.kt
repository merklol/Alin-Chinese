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

import com.maximapps.authentication.domain.models.UserInput
import com.maximapps.authentication.domain.models.ValidationResult
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test

class UserInputValidatorTest {

    @Test
    fun `when input is valid returns an empty result`() {
        val validator = UserInputValidator()
        val result = validator.validate(UserInput("admin@gmail.com", "abc123"))
        assertThat(result.validationResult, `is`(emptyList()))
    }

    @Test
    fun `when email is empty returns a result that contains InvalidEmail`() {
        val validator = UserInputValidator()
        val result = validator.validate(UserInput("", "abc123"))
        assertTrue(result.validationResult.any { it is ValidationResult.InvalidEmail })
    }

    @Test
    fun `when password is empty returns a result that contains InvalidPassword`() {
        val validator = UserInputValidator()
        val result = validator.validate(UserInput("admin@gmail.com", ""))
        assertTrue(result.validationResult.any { it is ValidationResult.InvalidPassword })
    }

    @Test
    fun `when input is empty returns a result that contains InvalidEmail & InvalidPassword`() {
        val validator = UserInputValidator()
        val result = validator.validate(UserInput("", ""))
        assertTrue(result.validationResult.all {
            it is ValidationResult.InvalidEmail || it is ValidationResult.InvalidPassword
        })
    }

    @Test
    fun `when email is invalid returns a result that contains InvalidEmail`() {
        val validator = UserInputValidator()
        val result = validator.validate(UserInput("lorem ipsum", "abc123"))
        assertTrue(result.validationResult.any { it is ValidationResult.InvalidEmail })
    }

    @Test
    fun `when password is invalid returns a result that contains InvalidPassword`() {
        val validator = UserInputValidator()
        val result = validator.validate(UserInput("admin@gmail.com", "abc"))
        assertTrue(result.validationResult.any { it is ValidationResult.InvalidPassword })
    }

    @Test
    fun `when input is invalid returns a result that contains InvalidEmail & InvalidPassword`() {
        val validator = UserInputValidator()
        val result = validator.validate(UserInput("lorem ipsum", "abc"))
        assertTrue(result.validationResult.all {
            it is ValidationResult.InvalidEmail || it is ValidationResult.InvalidPassword
        })
    }
}
