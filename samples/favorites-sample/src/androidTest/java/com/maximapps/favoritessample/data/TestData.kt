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

package com.maximapps.favoritessample.data

import android.app.Instrumentation
import com.maximapps.core.data.models.LessonEntity
import com.maximapps.core.utils.toByteArray
import com.maximapps.testutils.readBitmapFromAssets

class TestData(private val instrumentation: Instrumentation) {
    operator fun invoke() = listOf(
        LessonEntity(
            id = 0,
            email = "admin@gmail.com",
            title = "Common Chinese Grammar Structures for 的 (de) vs. 得 (de) vs. 地(de)",
            shortTitle = "Lesson 1",
            description = "",
            content = "[]",
            image = instrumentation
                .readBitmapFromAssets("sample_image.jpg").toByteArray(),
            date = "20.20.2020"
        ),
        LessonEntity(
            id = 1,
            email = "admin@gmail.com",
            title = "Watching videos is definitely one of the interesting and best ways to alleviate the pressure",
            shortTitle = "Lesson 2",
            description = "",
            content = "[]",
            image = instrumentation
                .readBitmapFromAssets("sample_image.jpg").toByteArray(),
            date = "20.20.2020"
        ),
        LessonEntity(
            id = 2,
            email = "admin@gmail.com",
            title = "Traveling in China – Business Etiquette and Culture",
            shortTitle = "Lesson 3",
            description = "",
            content = "[]",
            image = instrumentation
                .readBitmapFromAssets("sample_image.jpg").toByteArray(),
            date = "20.20.2020"
        )
    )
}
