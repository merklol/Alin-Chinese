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

import android.content.Context
import androidx.core.os.bundleOf
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.domain.repositories.CrudRepository
import com.maximapps.core.data.repositories.DefaultCrudRepository
import com.maximapps.core.utils.toByteArray
import com.maximapps.home.R
import com.maximapps.home.ui.preview.PreviewFragment
import com.maximapps.homesample.di.DatabaseModule
import com.maximapps.homesample.hilt.HiltContainerConfig
import com.maximapps.homesample.hilt.launchFragmentInHiltContainer
import com.maximapps.testutils.readBitmapFromAssets
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import io.mockk.mockk
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@SmallTest
@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class PreviewDialogTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    private lateinit var navController: TestNavHostController

    @Module
    @InstallIn(SingletonComponent::class)
    object TestHomeModule {
        @Provides
        @Singleton
        fun providesCrudRepository(): CrudRepository {
            return DefaultCrudRepository(mockk(relaxed = true), mockk(relaxed = true))
        }
    }

    @Before
    fun setup() {
        hiltRule.inject()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun whenOpenButtonClickedNavigateToPageFragment() {
        launchFragment(
            sharedLesson = SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                body = "[]",
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = "29.05.2022"
            )
        )
        onView(withId(R.id.openBtn)).perform(click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.pageFragment))
    }

    @Test
    fun whenArgumentIsNotNullShowPreviewData() {
        launchFragment(
            sharedLesson = SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                body = "[]",
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = "29.05.2022"
            )
        )
        onView(withId(R.id.imageHolder)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.openBtn)).check(matches(isDisplayed()))
    }

    @Test
    fun whenArgumentIsNotNullShowSnackBar() {
        launchFragment(null)
        onView(withText(R.string.error_preview_message)).check(matches(isDisplayed()))
    }

    @Test
    fun whenFavoriteBtnClickedShowMessage() {
        launchFragment(
            sharedLesson = SharedLesson(
                id = 0,
                primaryTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                secondaryTitle = "Lesson",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                body = "[]",
                thumbnail = getInstrumentation().readBitmapFromAssets("sample_image.jpg")
                    .toByteArray(),
                date = "29.05.2022"
            )
        )

        onView(withId(R.id.favoriteBtn)).perform(click())
        onView(withSubstring(getString(R.string.added_to_favorites))).check(matches(isDisplayed()))
    }

    private fun getString(resId: Int): String {
        val context: Context = ApplicationProvider.getApplicationContext()
        return context.getString(resId, "")
    }

    private fun launchFragment(sharedLesson: SharedLesson?) {
        launchFragmentInHiltContainer<PreviewFragment, HiltTestActivity>(
            HiltContainerConfig(
                fragmentArgs = bundleOf("sharedLesson" to sharedLesson),
                navController = navController,
                navResId = R.navigation.home_graph
            )
        ) { navController.setCurrentDestination(R.id.previewFragment) }
    }
}
