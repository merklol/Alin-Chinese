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
import android.graphics.Typeface
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.maximapps.page.data.models.Alignment
import com.maximapps.page.data.models.Bold
import com.maximapps.page.data.models.InlineStyle
import com.maximapps.page.data.models.Italic
import com.maximapps.page.data.models.Link
import com.maximapps.page.data.models.Underline
import com.maximapps.page.ui.common.layout.Padding
import com.maximapps.page.ui.common.modifiers.Modifier
import com.maximapps.page.ui.common.styles.TextStyle

/**
 * Creates a text view.
 *
 * @param context [Context]
 * @param text the text to display
 * @param modifier [Modifier]
 * @since 0.1
 */
fun text(context: Context, text: CharSequence, textStyle: TextStyle, modifier: Modifier) =
    TextView(context).also {
        it.setLayoutParams(modifier)
        it.setTextColor(ContextCompat.getColor(context, textStyle.colorResId))
        it.textSize = textStyle.size
        it.letterSpacing = textStyle.letterSpacing
        it.applyAlignment(modifier.alignment)
        it.applyBackground(textStyle.backgroundResId)
        it.applyPadding(modifier.padding)
        it.setText(text, textStyle.inline)
    }

fun TextView.applyBackground(backgroundResId: Int?) = backgroundResId?.let {
    background = ContextCompat.getDrawable(context, backgroundResId)
}

fun TextView.applyPadding(padding: Padding?) = padding?.let {
    val (start, top, end, bottom) = padding
    setPadding(start, top, end, bottom)
}

fun TextView.applyAlignment(alignment: Alignment?) = alignment?.let {
    gravity = when (alignment) {
        Alignment.Start -> Gravity.START
        Alignment.Center -> Gravity.CENTER
        Alignment.End -> Gravity.END
    }
}

fun TextView.setLayoutParams(modifier: Modifier) {
    val (start, top, end, bottom) = modifier.margin
    val params = LinearLayoutCompat.LayoutParams(modifier.width, modifier.height)
    params.setMargins(start, top, end, bottom)
    layoutParams = params
}

fun TextView.setText(text: CharSequence, inlineStyle: List<InlineStyle>?) = when (inlineStyle) {
    null -> this.text = text
    else -> {
        val spannableString = SpannableString(text)
        inlineStyle.forEach {
            when (it) {
                is Bold -> spannableString.setSpan(
                    StyleSpan(Typeface.BOLD), it.start, it.end,
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                is Italic -> spannableString.setSpan(
                    StyleSpan(Typeface.ITALIC), it.start, it.end,
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                is Underline -> spannableString.setSpan(
                    UnderlineSpan(), it.start, it.end,
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                is Link -> {
                    spannableString.setSpan(
                        URLSpan(it.href), it.start, it.end,
                        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    movementMethod = LinkMovementMethod.getInstance()
                }
            }
        }
        this.text = spannableString
    }
}
