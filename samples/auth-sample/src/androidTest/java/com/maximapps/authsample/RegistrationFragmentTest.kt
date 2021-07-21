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

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.maximapps.authentication.data.repositories.AuthenticationRepository
import com.maximapps.authentication.domain.validators.UserInputValidator
import com.maximapps.authentication.ui.registration.RegistrationFragment
import com.maximapps.authsample.data.provideRepositoryLoadingResult
import com.maximapps.authsample.di.AuthenticationModule
import com.maximapps.core.fragmentfactory.InjectFragmentFactory
import com.maximapps.authsample.hilt.HiltContainerConfig
import com.maximapps.authsample.hilt.launchFragmentInHiltContainer
import com.maximapps.testutils.waitFor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@UninstallModules(AuthenticationModule::class)
class RegistrationFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    private lateinit var navController: TestNavHostController

    @Inject
    lateinit var factory: InjectFragmentFactory

    @Module
    @InstallIn(ViewModelComponent::class)
    object TestAuthenticationModule {
        @Provides
        fun providesCredentialsValidator(): UserInputValidator = UserInputValidator()

        @Provides
        fun providesAuthenticationRepository(): AuthenticationRepository =
            provideRepositoryLoadingResult()
    }

    @Before
    fun setup() = hiltRule.inject()

    @Test
    fun whenLoginAndPasswordAreValidShowProgressIndicator() {
        launchFragment()
        onView(withHint(R.string.email_address_hint))
            .perform(click(), typeText("admin@gmail.com"), closeSoftKeyboard())
        onView(withHint(R.string.password_hint))
            .perform(click(), typeText("abc123"), closeSoftKeyboard())
        onView(withId(R.id.registerBtn)).perform(click())
        onView(ViewMatchers.isRoot()).perform(waitFor(1))
        onView(withId(R.id.progressIndicator)).check(matches(isDisplayed()))
    }

    @Test
    fun whenLoginInputIsInvalidShowErrorMessage() {
        launchFragment()
        onView(withHint(R.string.email_address_hint))
            .perform(click(), typeText("lorem ipsum"), closeSoftKeyboard())
        onView(withHint(R.string.password_hint))
            .perform(click(), typeText("abc123"), closeSoftKeyboard())
        onView(withId(R.id.registerBtn)).perform(click())
        onView(withText(R.string.invalid_email)).check(matches(isDisplayed()))
    }

    @Test
    fun whenPasswordInputIsInvalidShowErrorMessage() {
        launchFragment()
        onView(withHint(R.string.email_address_hint))
            .perform(click(), typeText("admin@gmail.com"), closeSoftKeyboard())
        onView(withHint(R.string.password_hint))
            .perform(click(), typeText("lorem ipsum"), closeSoftKeyboard())
        onView(withId(R.id.registerBtn)).perform(click())
        onView(withText(R.string.invalid_password)).check(matches(isDisplayed()))
    }

    @Test
    fun whenSignUpButtonClickedNavigateToRegistrationScreen() {
        launchFragment(R.id.registrationFragment)
        onView(withId(R.id.navigateUpBtn)).perform(click())
        assertThat(navController.currentDestination?.id, `is`(R.id.loginFragment))
    }

    private fun launchFragment(destId: Int? = null) {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<RegistrationFragment, HiltTestActivity>(
            HiltContainerConfig(
                navController = navController,
                navResId = R.navigation.authentication_graph,
                factory = factory
            )
        ) { destId?.let { navController.setCurrentDestination(it) } }
    }
}
