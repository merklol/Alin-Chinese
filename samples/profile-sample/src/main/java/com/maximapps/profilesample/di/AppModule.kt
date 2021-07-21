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

package com.maximapps.profilesample.di

import com.maximapps.core.navigation.GlobalDirections
import com.maximapps.core.navigation.GlobalNavHost
import com.maximapps.profile.ui.profile.ProfileFragmentDirections
import com.maximapps.profilesample.BuildConfig
import com.maximapps.profilesample.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesVersionName(): String = BuildConfig.VERSION_NAME

    @Provides
    fun providesGlobalNavHost(): GlobalNavHost = GlobalNavHost(R.id.navHostFragment)

    @Provides
    fun providesGlobalDirections(): GlobalDirections =
        object : GlobalDirections {
            override fun fromAuthenticationToMain() =
                ProfileFragmentDirections.actionProfileFragmentToAuthenticationFragment()

            override fun fromMainToAuthentication() =
                ProfileFragmentDirections.actionProfileFragmentToAuthenticationFragment()
        }
}
