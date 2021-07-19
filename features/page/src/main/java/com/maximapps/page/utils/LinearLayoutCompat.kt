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

package com.maximapps.page.utils

import android.content.Context
import androidx.appcompat.widget.LinearLayoutCompat
import com.maximapps.page.data.models.Blockquote
import com.maximapps.page.data.models.Divider
import com.maximapps.page.data.models.Header1
import com.maximapps.page.data.models.Header2
import com.maximapps.page.data.models.Header3
import com.maximapps.page.data.models.Image
import com.maximapps.page.data.models.Node
import com.maximapps.page.data.models.Paragraph
import com.maximapps.page.data.models.UnorderedList
import com.maximapps.page.ui.common.views.blockquote
import com.maximapps.page.ui.common.views.divider
import com.maximapps.page.ui.common.views.heading1
import com.maximapps.page.ui.common.views.heading2
import com.maximapps.page.ui.common.views.heading3
import com.maximapps.page.ui.common.views.image
import com.maximapps.page.ui.common.views.paragraph
import com.maximapps.page.ui.common.views.unorderedList

/**
 * Adds a list of nodes.
 *
 * @param context the context
 * @param nodes the list of nodes to add
 * @since 0.1
 */
fun LinearLayoutCompat.addViews(context: Context, nodes: List<Node>) {
    nodes.forEach {
        when (it) {
            is Header1 -> addView(heading1(context, it.value, it.inlineStyle, it.alignment))
            is Header2 -> addView(heading2(context, it.value, it.inlineStyle, it.alignment))
            is Header3 -> addView(heading3(context, it.value, it.inlineStyle, it.alignment))
            is Paragraph -> addView(paragraph(context, it.value, it.inlineStyle, it.alignment))
            is Image -> addView(image(context = context, link = it.link))
            is UnorderedList -> addView(unorderedList(context = context, items = it.items))
            is Blockquote -> addView(blockquote(context = context, text = it.value))
            is Divider -> addView(divider(context = context))
        }
    }
}
