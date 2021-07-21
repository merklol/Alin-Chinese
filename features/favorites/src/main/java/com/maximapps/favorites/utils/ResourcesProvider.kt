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

package com.maximapps.favorites.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.maximapps.favorites.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Returns resources from the XML.
 *
 * @since 0.3.1
 */
data class ResourcesProvider @Inject constructor(
    @ApplicationContext val context: Context
) {
    val deleteIcon
        get() =
            ContextCompat.getDrawable(context, R.drawable.ic_delete)?.toBitmap()

    val redColor get() = ContextCompat.getColor(context, R.color.red)

    val snackBarActionText get() = context.getString(R.string.snack_bar_action_text)
}
