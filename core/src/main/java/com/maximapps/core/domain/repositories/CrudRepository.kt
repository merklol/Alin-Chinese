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

package com.maximapps.core.domain.repositories

import android.content.Context
import com.maximapps.core.data.models.LessonEntity
import com.maximapps.core.data.repositories.sampledata.provideSampleData
import com.maximapps.core.domain.Lesson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository to perform generic CRUD operations on a database.
 *
 * @since 0.1
 */
interface CrudRepository {

    /**
     * Fetches favorite lessons from the app database.
     *
     * @return [Flow]
     * @since 0.1
     */
    fun getFavoriteLessons(): Flow<List<LessonEntity>>

    /**
     * Saves a lesson to the app database.
     */
    suspend fun saveToFavorites(lesson: Lesson): Any?

    /**
     * Removes a lesson from the app database.
     */
    suspend fun removeFromFavorites(lesson: Lesson): Any?

    /**
     * Fake implementation of the CrudRepository interface.
     *
     * @since 0.1
     */
    class Fake @Inject constructor(private val context: Context) : CrudRepository {

        override fun getFavoriteLessons() = flow {
            emit(provideSampleData(context))
        }

        override suspend fun saveToFavorites(lesson: Lesson): Any? = null

        override suspend fun removeFromFavorites(lesson: Lesson): Any? = null
    }
}
