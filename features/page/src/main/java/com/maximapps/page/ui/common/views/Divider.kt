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
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.maximapps.page.R
import com.maximapps.page.ui.common.layout.Margin
import com.maximapps.page.ui.common.modifiers.Modifier
import com.maximapps.page.ui.common.unit.dp
import com.maximapps.page.utils.isDarkTheme

/**
 * Creates a divider.
 *
 * @param context [Context]
 * @param modifier [Modifier] to define new styles
 * @since 0.1
 */
internal fun divider(
    context: Context,
    modifier: Modifier = Modifier(
        width = LinearLayoutCompat.LayoutParams.MATCH_PARENT,
        height = 1.dp,
        margin = Margin(0, 32.dp, 0, 32.dp)
    )
) = View(context).also {
    val (start, top, end, bottom) = modifier.margin
    val params = LinearLayoutCompat.LayoutParams(modifier.width, modifier.height)
    params.setMargins(start, top, end, bottom)
    it.layoutParams = params
    it.setBackgroundColor(
        context.getColor(
            when (context.isDarkTheme()) {
                true -> R.color.gray_800
                else -> R.color.gray_500
            }
        )
    )
}
