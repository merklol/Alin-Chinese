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

package com.maximapps.authentication.ui.authentication

import androidx.lifecycle.viewModelScope
import com.maximapps.authentication.data.repositories.AuthenticationRepository
import com.maximapps.authentication.domain.models.UserInput
import com.maximapps.authentication.domain.models.ValidationResult
import com.maximapps.authentication.domain.validators.UserInputValidator
import com.maximapps.authentication.ui.toAuthenticationStates
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.coroutines.onFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
    private val validator: UserInputValidator
) : AndroidDataFlow(UIState.Empty) {

    val isUserAuthenticated get() = repository.isUserAuthenticated()

    fun login(userInput: UserInput) = validator.validate(userInput).also {
        when (it.isValid()) {
            true -> performLogin(userInput)
            else -> showErrors(it.validationResult)
        }
    }

    fun register(userInput: UserInput) {
        validator.validate(userInput).also {
            when (it.isValid()) {
                true -> performRegistration(userInput)
                else -> showErrors(it.validationResult)
            }
        }
    }

    private fun performRegistration(userInput: UserInput) = viewModelScope.launch {
        onFlow({ repository.register(userInput) }) {
            setState { it.toAuthenticationStates() }
        }
    }

    private fun performLogin(userInput: UserInput) = viewModelScope.launch {
        onFlow({ repository.login(userInput) }) {
            setState { it.toAuthenticationStates() }
        }
    }

    private fun showErrors(errors: List<ValidationResult>) = action {
        setState { AuthenticationStates.InvalidFormatState(errors) }
    }
}
