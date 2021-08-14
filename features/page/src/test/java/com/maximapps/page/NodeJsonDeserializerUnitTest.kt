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
import com.google.gson.reflect.TypeToken
import com.maximapps.page.data.deserializers.AlignmentDeserializer
import com.maximapps.page.data.deserializers.InlineStyleJsonDeserializer
import com.maximapps.page.data.deserializers.NodeJsonDeserializer
import com.maximapps.page.data.models.Alignment
import com.maximapps.page.data.models.Blockquote
import com.maximapps.page.data.models.Divider
import com.maximapps.page.data.models.Heading1
import com.maximapps.page.data.models.Heading2
import com.maximapps.page.data.models.Heading3
import com.maximapps.page.data.models.Image
import com.maximapps.page.data.models.InlineStyle
import com.maximapps.page.data.models.ListItem
import com.maximapps.page.data.models.Node
import com.maximapps.page.data.models.Paragraph
import com.maximapps.page.data.models.UnorderedList
import com.maximapps.page.exceptions.InvalidNodeTypeException
import com.maximapps.page.ext.readFileFromResources
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class NodeJsonDeserializerUnitTest {
    private val type = object : TypeToken<List<Node>>() {}.type
    private val classLoader = javaClass.classLoader
    private val gson = GsonBuilder()
        .registerTypeAdapter(Node::class.java, NodeJsonDeserializer())
        .registerTypeAdapter(InlineStyle::class.java, InlineStyleJsonDeserializer())
        .registerTypeAdapter(Alignment::class.java, AlignmentDeserializer())
        .create()


    @Test
    fun `when node type is header1 then returns a list of nodes with a Header1 object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk.json")
        val expected = listOf(
            Heading1(alignment = Alignment.Center, inlineStyles = null, value = "test")
        )
        assertThat(gson.fromJson<List<Node>>(json, type), `is`(expected))
    }

    @Test
    fun `when node type is header2 then returns a list of nodes with a Header2 object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk2.json")
        val expected = listOf(
            Heading2(alignment = Alignment.Center, inlineStyles = null, value = "test")
        )
        assertThat(gson.fromJson<List<Node>>(json, type), `is`(expected))
    }

    @Test
    fun `when node type is header3 then returns a list of nodes with a Header3 object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk3.json")
        val expected = listOf(
            Heading3(alignment = Alignment.Center, inlineStyles = null, value = "test")
        )
        assertThat(gson.fromJson<List<Node>>(json, type), `is`(expected))
    }

    @Test
    fun `when node type is paragraph then returns a list of nodes with a Paragraph object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk4.json")
        val expected = listOf(
            Paragraph(alignment = Alignment.Center, inlineStyles = null, value = "test")
        )
        assertThat(gson.fromJson<List<Node>>(json, type), `is`(expected))
    }

    @Test
    fun `when node type is unorderedList then returns a list of nodes with a UnorderedList object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk5.json")
        val expected = listOf(
            UnorderedList(
                items = listOf(
                    ListItem(value = "item 1"),
                    ListItem(value = "item 2"),
                    ListItem(value = "item 3"),
                    ListItem(value = "item 4")
                )
            )
        )
        assertThat(gson.fromJson<List<Node>>(json, type), `is`(expected))
    }

    @Test
    fun `when node type is image then returns a list of nodes with a Image object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk6.json")
        assertThat(gson.fromJson<List<Node>>(json, type), `is`(listOf(Image(link = ""))))
    }

    @Test
    fun `when node type is divider then returns a list of nodes with a Divider object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk7.json")
        assertThat(gson.fromJson<List<Node>>(json, type)[0], instanceOf(Divider::class.java))
    }

    @Test
    fun `when node type is blockquote then returns a list of nodes with a Blockquote object`() {
        val json = classLoader?.readFileFromResources("nodes/chunk8.json")
        assertThat(gson.fromJson<List<Node>>(json, type), `is`(listOf(Blockquote(value = "test"))))
    }

    @Test
    fun `when node type is invalid then throws an exception`() {
        val json = classLoader?.readFileFromResources("nodes/chunk9.json")
        assertThrows(InvalidNodeTypeException::class.java) {
            gson.fromJson<List<Node>>(json, type)
        }
    }
}
