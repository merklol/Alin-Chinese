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

package com.maximapps.authentication.ui.registration

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.maximapps.authentication.R
import com.maximapps.authentication.databinding.FragmentRegistrationBinding
import com.maximapps.authentication.domain.models.UserInput
import com.maximapps.authentication.domain.models.ValidationResult
import com.maximapps.authentication.ext.text
import com.maximapps.authentication.ui.authentication.AuthenticationStates
import com.maximapps.authentication.ui.authentication.AuthenticationViewModel
import com.maximapps.core.navigation.GlobalDirections
import com.maximapps.core.navigation.GlobalNavHost
import com.maximapps.core.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment @Inject constructor(
    private val navHost: GlobalNavHost,
    private val directions: GlobalDirections
) : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderLoginStates()
        binding.registerBtn.setOnClickListener(::onBtnClicked)
        binding.navigateUpBtn.setOnClickListener(::onBtnClicked)
    }

    private fun onBtnClicked(view: View) {
        when (view.id) {
            R.id.registerBtn -> viewModel.register(
                UserInput(binding.emailInput.text, binding.passwordInput.text)
            )
            else -> navigate(
                RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
            )
        }
    }

    private fun renderLoginStates() = onStates(viewModel) { state ->
        when (state) {
            is AuthenticationStates.Loading -> showProgressBar()
            is AuthenticationStates.Success -> {
                navigate(directions.fromAuthenticationToMain(), navHost)
            }
            is AuthenticationStates.Failed -> {
                showRegistrationForm()
                state.message?.let {
                    Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                }
            }
            is AuthenticationStates.InvalidFormatState -> renderErrorMessages(state.errors)
        }
    }

    private fun renderErrorMessages(errors: List<ValidationResult>) = errors.forEach {
        when (it) {
            is ValidationResult.InvalidEmail ->
                binding.emailInput.error = getString(it.error)
            is ValidationResult.InvalidPassword ->
                binding.passwordInput.error = getString(it.error)
        }
    }

    private fun showRegistrationForm() {
        binding.group.isVisible = true
        binding.progressIndicator.isVisible = false
    }

    private fun showProgressBar() {
        binding.group.isVisible = false
        binding.progressIndicator.isVisible = true
    }
}
