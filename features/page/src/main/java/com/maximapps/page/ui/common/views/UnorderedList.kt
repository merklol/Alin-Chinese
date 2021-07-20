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

package com.maximapps.page.ui.common.views

import android.content.Context
import android.text.SpannableString
import android.text.style.BulletSpan
import com.maximapps.page.R
import com.maximapps.page.data.models.ListItem
import com.maximapps.page.ui.common.modifiers.Modifier
import com.maximapps.page.ui.common.styles.TextStyle
import com.maximapps.page.utils.isDarkTheme

private const val GAP_WIDTH = 16

/**
 * Creates an unordered list.
 *
 * @param context [Context]
 * @param items a list of the [ListItem]
 * @param textStyle [TextStyle] to define a custom presentation of the list
 * @since 0.1
 */
internal fun unorderedList(
    context: Context, items: List<ListItem>, textStyle: TextStyle =
        TextStyle(
            size = 16f,
            colorResId = when (context.isDarkTheme()) {
                true -> R.color.gray_300
                else -> R.color.dark_green_700
            }
        )
) = text(context, items.toBulletedList(), textStyle, Modifier())

private fun List<ListItem>.toBulletedList(): CharSequence {
    val spannableString = SpannableString(this.joinToString("\n") { it.value })
    return spannableString.apply {
        this@toBulletedList.foldIndexed(0) { index, acc, span ->
            val end = acc + span.value.length + if (index != this@toBulletedList.size - 1) 1 else 0
            setSpan(BulletSpan(GAP_WIDTH), acc, end, 0)
            end
        }
    }
}
