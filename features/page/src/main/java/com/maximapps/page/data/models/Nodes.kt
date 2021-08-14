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

package com.maximapps.page.data.models

import com.google.gson.annotations.SerializedName

/**
 * Defines the base node type.
 *
 * @since 0.1
 */
internal sealed class Node

/**
 * Defines the most important heading.
 *
 * @since 0.1
 */
internal data class Heading1(
    val alignment: Alignment?,
    @SerializedName("inline-styles")
    val inlineStyles: List<InlineStyle>?,
    val value: String
) : Node()

/**
 * Defines the second heading.
 *
 * @since 0.1
 */
internal data class Heading2(
    val alignment: Alignment?,
    @SerializedName("inline-styles")
    val inlineStyles: List<InlineStyle>?,
    val value: String
) : Node()

/**
 * Defines the least important heading.
 *
 * @since 0.1
 */
internal data class Heading3(
    val alignment: Alignment?,
    @SerializedName("inline-styles")
    val inlineStyles: List<InlineStyle>?,
    val value: String
) : Node()

/**
 * Defines a paragraph.
 *
 * @since 0.1
 */
internal data class Paragraph(
    val alignment: Alignment?,
    @SerializedName("inline-styles")
    val inlineStyles: List<InlineStyle>?,
    val value: String
) : Node()

/**
 * Defines a blockquote.
 *
 * @since 0.1
 */
internal data class Blockquote(val value: String) : Node()

/**
 * Defines an image.
 *
 * @since 0.1
 */
internal data class Image(val link: String) : Node()

/**
 * Defines a collection of related items that have no special order or sequence.
 *
 * @since 0.1
 */
internal data class UnorderedList(val items: List<ListItem>) : Node()

/**
 * Defines an item of unordered list.
 *
 * @since 0.1
 */
internal data class ListItem(val value: String)

/**
 * Defines a horizontal rule.
 *
 * @since 0.1
 */
internal object Divider : Node()
