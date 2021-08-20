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

package com.maximapps.homesample

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.domain.repositories.CrudRepository
import com.maximapps.core.data.repositories.DefaultCrudRepository
import com.maximapps.core.utils.toByteArray
import com.maximapps.homesample.di.DatabaseModule
import com.maximapps.homesample.hilt.HiltContainerConfig
import com.maximapps.homesample.hilt.launchFragmentInHiltContainer
import com.maximapps.page.ui.page.PageFragment
import com.maximapps.testutils.readBitmapFromAssets
import com.maximapps.testutils.readJsonFromAssets
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import io.mockk.mockk
import org.hamcrest.core.StringContains.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@MediumTest
@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class PageFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    private lateinit var navController: NavController

    @Module
    @InstallIn(SingletonComponent::class)
    object TestHomeModule {
        @Provides
        @Singleton
        fun providesCrudRepository(): CrudRepository =
            DefaultCrudRepository(mockk(relaxed = true), mockk(relaxed = true))
    }

    @Before
    fun setup() {
        hiltRule.inject()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun whenContentIsNotEmptyDisplayItOnScreen() {
        val content = getInstrumentation().readJsonFromAssets("chunk.json")
        launchFragment(
            SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "",
                body = content,
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = ""
            )
        )
        onView(withText(containsString("They even sound"))).check(matches(isDisplayed()))
        onView(withText(containsString("It boils down"))).check(matches(isDisplayed()))
        onView(withText(containsString("的 + Noun"))).check(matches(isDisplayed()))
    }

    @Test
    fun whenArgumentIsNullShowSnackBarOnScreen() {
        launchFragment(null)
        onView(withText("Whoops! Something went wrong.")).check(matches(isDisplayed()))
    }

    @Test
    fun whenContentIsEmptyShowSnackBarOnScreen() {
        launchFragment(
            SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "",
                body = "[]",
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = ""
            )
        )
        onView(withText("Whoops! Something went wrong.")).check(matches(isDisplayed()))
    }

    @Test
    fun whenContentIsInvalidShowSnackBarOnScreen() {
        val content = getInstrumentation().readJsonFromAssets("chunk2.json")
        launchFragment(
            SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "",
                body = content,
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = ""
            )
        )
        onView(withText(R.string.error_page_message)).check(matches(isDisplayed()))
    }

    @Test
    fun whenRemoveFromFavoritesClickedShowSnackBarOnScreen() {
        val content = getInstrumentation().readJsonFromAssets("chunk3.json")
        launchFragment(
            SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "",
                body = content,
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = ""
            )
        )
        onView(withId(R.id.headerImage)).perform(swipeUp())
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.remove_from_favorites)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withSubstring("Lorem ipsum dolor")))
    }

    @Test
    fun whenAddToFavoritesClickedShowSnackBarOnScreen() {
        val content = getInstrumentation().readJsonFromAssets("chunk3.json")
        launchFragment(
            SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "",
                body = content,
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = ""
            )
        )
        onView(withId(R.id.headerImage)).perform(swipeUp())
        onView(withId(R.id.addToFavorites)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withSubstring("Lorem ipsum dolor")))
    }

    private fun launchFragment(sharedLesson: SharedLesson?) {
        launchFragmentInHiltContainer<PageFragment, HiltTestActivity>(
            HiltContainerConfig(
                fragmentArgs = bundleOf("sharedLesson" to sharedLesson),
                navController = navController,
                navResId = R.navigation.page_graph
            )
        )
    }
}
