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

package com.maximapps.authsample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import com.maximapps.authentication.data.repositories.AuthenticationRepository
import com.maximapps.authentication.domain.validators.UserInputValidator
import com.maximapps.authsample.data.provideRepositorySuccessResult
import com.maximapps.authsample.di.AuthenticationModule
import com.maximapps.testutils.waitFor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@HiltAndroidTest
@UninstallModules(AuthenticationModule::class)
class GlobalNavigationTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule: ActivityScenarioRule<HostActivity> =
        ActivityScenarioRule(HostActivity::class.java)

    @Module
    @InstallIn(ViewModelComponent::class)
    object TestAuthenticationModule {

        @Provides
        fun providesCredentialsValidator(): UserInputValidator = UserInputValidator()

        @Provides
        fun providesAuthenticationRepository(): AuthenticationRepository =
            provideRepositorySuccessResult()
    }

    @Before
    fun setup() = hiltRule.inject()

    @Test
    fun whenUserIsAuthenticatedNavigateToMain() {
        onView(withHint(R.string.email_address_hint))
            .perform(click(), typeText("admin@gmail.com"), closeSoftKeyboard())

        onView(withHint(R.string.password_hint))
            .perform(click(), typeText("abc123"), closeSoftKeyboard())

        onView(withId(R.id.loginBtn)).perform(click())
        onView(ViewMatchers.isRoot()).perform(waitFor(1))
        onView(withText(R.string.main_content)).check(matches(isDisplayed()))
    }
}
