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

import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import com.google.firebase.firestore.QuerySnapshot
import com.maximapps.core.fragmentfactory.InjectFragmentFactory
import com.maximapps.home.R
import com.maximapps.home.data.models.Lesson
import com.maximapps.home.di.HomeModule
import com.maximapps.home.ui.home.HomeFragment
import com.maximapps.home.ui.lessonlist.LessonListAdapter
import com.maximapps.homesample.data.pagingSourceSuccessResult
import com.maximapps.homesample.hilt.HiltContainerConfig
import com.maximapps.homesample.hilt.launchFragmentInHiltContainer
import com.maximapps.testutils.waitFor
import com.maximapps.testutils.waitUntilGone
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@SmallTest
@HiltAndroidTest
@UninstallModules(HomeModule::class)
class HomeFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    private lateinit var navController: NavController

    @Inject
    lateinit var fragmentFactory: InjectFragmentFactory

    @Module
    @InstallIn(SingletonComponent::class)
    object TestHomeModule {
        @Singleton
        @Provides
        fun providesPagingConfig(): PagingConfig =
            PagingConfig(pageSize = 10, enablePlaceholders = false)

        @Singleton
        @Provides
        fun providesFirestorePagingSource(): PagingSource<QuerySnapshot, Lesson> =
            pagingSourceSuccessResult
    }

    @Before
    @ExperimentalCoroutinesApi
    fun setup() {
        hiltRule.inject()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<HomeFragment, HiltTestActivity>(
            HiltContainerConfig(
                navController = navController,
                navResId = R.navigation.home_graph,
                factory = fragmentFactory
            )
        )
    }

    @Test
    fun whenCollectionExistsLoadAndDisplayLessons() {
        onView(withId(R.id.progress)).perform(waitUntilGone(5))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun whenLessonListItemClickedNavigateToPreviewFragment() {
        val action =
            actionOnItemAtPosition<LessonListAdapter.RegularListItemViewHolder>(0, click())

        onView(withId(R.id.progress)).perform(waitUntilGone(1))
        onView(ViewMatchers.isRoot()).perform(waitFor(5))
        onView(withId(R.id.recyclerView)).perform(action)

        assertThat(navController.currentDestination?.id, equalTo(R.id.previewFragment))
    }
}
