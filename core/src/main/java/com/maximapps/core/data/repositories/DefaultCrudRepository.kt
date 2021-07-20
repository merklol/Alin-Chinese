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

package com.maximapps.core.data.repositories

import com.maximapps.core.data.db.FavoriteLessonsDao
import com.maximapps.core.domain.preferences.UserPreferences
import com.maximapps.core.domain.Lesson
import com.maximapps.core.domain.repositories.CrudRepository
import com.maximapps.core.domain.toLessonEntity
import com.maximapps.core.utils.execute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Default implementation of the CrudRepository interface.
 *
 * @since 0.1
 */
class DefaultCrudRepository(
    private val favoriteLessonsDao: FavoriteLessonsDao,
    private val userPreferences: UserPreferences
) : CrudRepository {

    private val email get() = userPreferences.email ?: ""

    override fun getFavoriteLessons() =
        favoriteLessonsDao.getFavoriteLessons(email)

    override suspend fun saveToFavorites(lesson: Lesson) = withContext(Dispatchers.IO) {
        execute { favoriteLessonsDao.saveLesson(lesson.toLessonEntity(email)) }
    }

    override suspend fun removeFromFavorites(lesson: Lesson) = withContext(Dispatchers.IO) {
        execute { favoriteLessonsDao.removeLesson(lesson.toLessonEntity(email)) }
    }
}
