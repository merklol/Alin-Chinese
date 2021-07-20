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

package com.maximapps.home.data

import android.graphics.Bitmap
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.utils.toByteArray
import com.maximapps.home.data.models.Lesson
import com.maximapps.home.data.models.Source

/**
 * Converts a list of [Source] to a list of [Lesson]
 *
 * @since 0.1
 */
internal fun MutableList<Source>.toLessons() = map {
    Lesson(
        id = it.id,
        template = it.template,
        title = it.title,
        shortTitle = it.shortTitle,
        description = it.description,
        content = it.content,
        imageSrc = it.imageSrc,
        date = it.date
    )
}

/**
 * Converts a lesson into a shared lesson.
 *
 * @since 0.1
 */
internal fun Lesson.toSharedLesson(imageBitmap: Bitmap) = SharedLesson(
    id = id,
    title = title,
    shortTitle = shortTitle,
    description = description,
    content = content,
    image = imageBitmap.toByteArray(),
    date = date
)
