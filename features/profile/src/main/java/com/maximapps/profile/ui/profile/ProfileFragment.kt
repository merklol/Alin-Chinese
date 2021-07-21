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

package com.maximapps.profile.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maximapps.core.navigation.GlobalDirections
import com.maximapps.core.navigation.GlobalNavHost
import com.maximapps.core.navigation.navigate
import com.maximapps.profile.R
import com.maximapps.profile.databinding.FragmentProfileBinding
import com.maximapps.profile.ui.logOutDialog
import com.maximapps.profile.utils.openLink
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor(
    private val navHost: GlobalNavHost,
    private val profileDirections: GlobalDirections,
    private val versionName: String
) : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emailView.text = viewModel.email
        binding.versionView.text = getString(R.string.app_version_name, versionName)
        binding.privacyPolicyView.setOnClickListener(::onViewClicked)
        binding.rateAppView.setOnClickListener(::onViewClicked)
        binding.logOutView.setOnClickListener(::onViewClicked)
    }

    private fun onViewClicked(view: View) = when (view.id) {
        R.id.rateAppView -> {
            openLink("market://details?id=com.maximcode.mccalculator")
            true
        }

        R.id.privacyPolicyView -> {
            openLink("http://google.com")
            true
        }

        R.id.logOutView -> {
            logOutDialog(requireContext()) {
                viewModel.setAuthenticationState()
                navigate(profileDirections.fromMainToAuthentication(), navHost)
            }.show()
            true
        }

        else -> false
    }
}
