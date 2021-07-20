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

package com.maximapps.page.ui.page

import androidx.lifecycle.viewModelScope
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.data.repositories.CrudRepository
import com.maximapps.core.domain.toLesson
import com.maximapps.page.data.ContentParser
import com.maximapps.page.exceptions.InvalidAlignmentTypeException
import com.maximapps.page.exceptions.InvalidInlineStyleTypeException
import com.maximapps.page.exceptions.InvalidNodeTypeException
import com.maximapps.page.utils.attempt
import com.maximapps.page.utils.catch
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val parser: ContentParser,
    private val crudRepository: CrudRepository,
) : AndroidDataFlow(UIState.Empty) {

    fun createPage(source: String) = action {
        setState {
            attempt {
                PageStates.Parsed(parser(source))
            }.catch(
                InvalidNodeTypeException::class, InvalidAlignmentTypeException::class,
                InvalidInlineStyleTypeException::class
            ) {
                PageStates.Error
            }
        }
    }

    fun saveToFavorites(sharedLesson: SharedLesson) = viewModelScope.launch {
        crudRepository.saveToFavorites(sharedLesson.toLesson())
    }

    fun removeFromFavorites(sharedLesson: SharedLesson) = viewModelScope.launch {
        crudRepository.removeFromFavorites(sharedLesson.toLesson())
    }
}
