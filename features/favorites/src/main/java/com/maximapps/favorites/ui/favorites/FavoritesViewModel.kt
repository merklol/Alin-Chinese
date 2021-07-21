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

package com.maximapps.favorites.ui.favorites

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.maximapps.core.domain.Lesson
import com.maximapps.core.domain.repositories.CrudRepository
import com.maximapps.core.domain.toLesson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val crudRepository: CrudRepository,
) : AndroidDataFlow(FavoritesStates.Fetching) {

    fun favoriteLessons() = viewModelScope.launch {
        crudRepository.getFavoriteLessons()
            .map { lessons -> lessons.map { it.toLesson() } }
            .flowOn(Dispatchers.IO)
            .catch { Log.e(tag, "Database operation error", it) }
            .collect {
                setState { FavoritesStates.Loaded(it) }
            }
    }

    fun saveToFavorites(lesson: Lesson) = viewModelScope.launch {
        crudRepository.saveToFavorites(lesson)
    }

    fun removeFromFavorites(lesson: Lesson) = viewModelScope.launch {
        crudRepository.removeFromFavorites(lesson)
    }
}
