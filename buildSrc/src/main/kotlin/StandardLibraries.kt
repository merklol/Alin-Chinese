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

object StandardLibraries {

    /**
     * Unit testing framework.
     *
     * @since 0.5.2
     */
    const val JUnit = "junit:junit:${LibraryVersions.JUnit}"

    /**
     * Provides extensions for Android's common libraries.
     *
     * @since 0.5.2
     */
    const val CoreKtx = "androidx.core:core-ktx:${LibraryVersions.CoreKtx}"

    /**
     * Backport of the Android 4.0 ActionBar API.
     *
     * @since 0.5.2
     */
    const val AppCompat = "androidx.appcompat:appcompat:${LibraryVersions.AppCompat}"

    /**
     * Material Components for Android.
     *
     * @since 0.5.2
     */
    const val Material = "com.google.android.material:material:${LibraryVersions.Material}"

    /**
     * Provides an extensive framework for testing Android apps.
     *
     * @since 0.5.2
     */
    const val EspressoCore = "androidx.test.espresso:espresso-core:${LibraryVersions.Espresso}"

    /**
     * Unit testing framework extensions for Android.
     *
     * @since 0.5.2
     */
    const val AndroidXJunit = "androidx.test.ext:junit:${LibraryVersions.AndroidXJunit}"

    /**
     * Extensions for testing fragments.
     *
     * @since 0.5.2
     */
    const val FragmentTesting =
        "androidx.fragment:fragment-testing:${LibraryVersions.FragmentTesting}"

    /**
     * Provides actions to test classes like DatePicker, DrawerLayout and RecyclerView
     *
     * @since 0.5.2
     */
    const val EspressoContrib =
        "androidx.test.espresso:espresso-contrib:${LibraryVersions.EspressoContrib}"

    /**
     * Provides classes for testing navigation in Fragment scenarios.
     *
     * @since 0.5.2
     */
    const val NavigationTesting =
        "androidx.navigation:navigation-testing:${LibraryVersions.NavigationTesting}"
}