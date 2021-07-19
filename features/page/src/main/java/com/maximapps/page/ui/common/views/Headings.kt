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
import com.maximapps.page.R
import com.maximapps.page.data.models.Alignment
import com.maximapps.page.data.models.InlineStyle
import com.maximapps.page.ui.common.modifiers.Modifier
import com.maximapps.page.ui.common.styles.TextStyle
import com.maximapps.page.utils.isDarkTheme

/**
 * Creates a heading 1.
 *
 * @param context [Context]
 * @param text the text to display
 * @param inlineStyle a list of [InlineStyle]
 * @param alignment [Alignment]
 * @since 0.1
 */
fun heading1(
    context: Context,
    text: CharSequence,
    inlineStyle: List<InlineStyle>?,
    alignment: Alignment?
) = text(
    context = context,
    text = text,
    textStyle = TextStyle(
        size = 24f,
        colorResId = when (context.isDarkTheme()) {
            true -> R.color.white
            else -> R.color.dark_green_900
        },
        inline = inlineStyle
    ),
    modifier = Modifier(alignment = alignment)
)

/**
 * Creates a heading 2.
 *
 * @param context [Context]
 * @param text the text to display
 * @param inlineStyle a list of [InlineStyle]
 * @param alignment [Alignment]
 * @since 0.1
 */
fun heading2(
    context: Context,
    text: CharSequence,
    inlineStyle: List<InlineStyle>?,
    alignment: Alignment?
) = text(
    context = context,
    text = text,
    textStyle = TextStyle(
        size = 20f,
        colorResId = when (context.isDarkTheme()) {
            true -> R.color.white
            else -> R.color.dark_green_900
        },
        inline = inlineStyle
    ),
    modifier = Modifier(alignment = alignment)
)

/**
 * Creates a heading 3.
 *
 * @param context [Context]
 * @param text the text to display
 * @param inlineStyle a list of [InlineStyle]
 * @param alignment [Alignment]
 * @since 0.1
 */
fun heading3(
    context: Context,
    text: CharSequence,
    inlineStyle: List<InlineStyle>?,
    alignment: Alignment?
) = text(
    context = context,
    text = text,
    textStyle = TextStyle(
        size = 18f,
        colorResId = when (context.isDarkTheme()) {
            true -> R.color.white
            else -> R.color.dark_green_900
        },
        inline = inlineStyle
    ),
    modifier = Modifier(alignment = alignment)
)
