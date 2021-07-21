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

package com.maximapps.favoritessample

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.platform.app.InstrumentationRegistry
import com.maximapps.core.domain.repositories.CrudRepository
import com.maximapps.core.data.repositories.DefaultCrudRepository
import com.maximapps.core.fragmentfactory.InjectFragmentFactory
import com.maximapps.favorites.ui.favorites.FavoritesFragment
import com.maximapps.favorites.ui.favoriteslist.FavoritesListAdapter
import com.maximapps.favoritessample.data.provideTestDao
import com.maximapps.favoritessample.di.DatabaseModule
import com.maximapps.favoritessample.hilt.HiltContainerConfig
import com.maximapps.favoritessample.hilt.launchFragmentInHiltContainer
import com.maximapps.testutils.waitUntilGone
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import io.mockk.mockk
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class FavoritesFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var factory: InjectFragmentFactory
    private lateinit var navController: TestNavHostController

    @Module
    @InstallIn(SingletonComponent::class)
    object TestDatabaseModule {
        @Provides
        @Singleton
        fun providesCrudRepository(): CrudRepository =
            DefaultCrudRepository(
                provideTestDao(
                    InstrumentationRegistry.getInstrumentation()
                ),
                mockk(relaxed = true)
            )
    }

    @Before
    fun setup() {
        hiltRule.inject()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<FavoritesFragment, HiltTestActivity>(
            HiltContainerConfig(
                navController = navController,
                navResId = R.navigation.favorites_graph,
                factory = factory
            )
        )
    }

    @Test
    fun whenDataFetchedShowRecyclerView() {
        onView(withId(R.id.progressBar)).perform(waitUntilGone(5))
        onView(withSubstring("Common Chinese")).check(matches(isDisplayed()))
        onView(withSubstring("Watching videos")).check(matches(isDisplayed()))
        onView(withSubstring("Traveling in")).check(matches(isDisplayed()))
    }

    @Test
    fun whenOnListItemClickedNavigatesToPageFragment() {
        val action = RecyclerViewActions
            .actionOnItemAtPosition<FavoritesListAdapter.ListItemViewHolder>(2, click())
        onView(withId(R.id.progressBar)).perform(waitUntilGone(5))
        onView(withId(R.id.favoritesList)).perform(action)
        assertThat(navController.currentDestination?.id, CoreMatchers.equalTo(R.id.pageFragment))
    }
}