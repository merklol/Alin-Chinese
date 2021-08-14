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

package com.maximapps.page

import com.google.gson.GsonBuilder
import com.maximapps.page.data.ContentParser
import com.maximapps.page.data.deserializers.AlignmentDeserializer
import com.maximapps.page.data.deserializers.InlineStylesJsonDeserializer
import com.maximapps.page.data.deserializers.NodeJsonDeserializer
import com.maximapps.page.data.models.Alignment
import com.maximapps.page.data.models.Blockquote
import com.maximapps.page.data.models.Image
import com.maximapps.page.data.models.InlineStyle
import com.maximapps.page.data.models.Node
import com.maximapps.page.data.models.Paragraph
import com.maximapps.page.ext.readFileFromResources
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ContentParserUnitTest {
    private val classLoader = javaClass.classLoader
    private val gson = GsonBuilder()
        .registerTypeAdapter(Node::class.java, NodeJsonDeserializer())
        .registerTypeAdapter(InlineStyle::class.java, InlineStylesJsonDeserializer())
        .registerTypeAdapter(Alignment::class.java, AlignmentDeserializer())
        .create()
    private val contentParser = ContentParser(gson)

    @Test
    fun `when nodes are not empty then returns a list of nodes`() {
        val json = classLoader?.readFileFromResources("nodes/chunk10.json")
        val expected = listOf(
            Paragraph(
                alignment = Alignment.End,
                inlineStyles = null,
                text = "Lorem ipsum"
            ),
            Image(link = ""),
            Blockquote(text = "Lorem ipsum"),
            Paragraph(
                alignment = Alignment.Start,
                inlineStyles = null,
                text = "Lorem ipsum"
            )
        )
        assertThat(contentParser(json!!), `is`(expected))
    }

    @Test
    fun `when nodes are empty then returns an empty list`() {
        assertThat(contentParser("[]"), `is`(listOf()))
    }
}
