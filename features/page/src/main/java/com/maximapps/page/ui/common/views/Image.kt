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
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import coil.load
import com.maximapps.page.R
import com.maximapps.page.ui.common.layout.Margin
import com.maximapps.page.ui.common.modifiers.Modifier
import com.maximapps.page.ui.common.unit.dp

/**
 * Creates an image.
 *
 * @param context [Context]
 * @param link the url to the image source
 * @param modifier [Modifier]
 * @since 0.1
 */
internal fun image(
    context: Context,
    link: String,
    modifier: Modifier = Modifier(
        width = 380.dp,
        height = 300.dp,
        margin = Margin(0, 16.dp, 0, 16.dp)
    )
) = ImageView(context).also {
    it.setLayoutParams(modifier)
    it.scaleType = ImageView.ScaleType.CENTER_CROP
    it.load(link) {
        crossfade(true)
        placeholder(R.drawable.ic_img_placeholder)
    }
}

private fun ImageView.setLayoutParams(modifier: Modifier) {
    val (start, top, end, bottom) = modifier.margin
    val params = LinearLayoutCompat.LayoutParams(modifier.width, modifier.height)
    params.setMargins(start, top, end, bottom)
    params.gravity = Gravity.CENTER
    layoutParams = params
}
