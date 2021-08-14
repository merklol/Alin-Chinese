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
import com.maximapps.page.data.deserializers.InlineStylesJsonDeserializer
import com.maximapps.page.data.models.Bold
import com.maximapps.page.data.models.InlineStyle
import com.maximapps.page.data.models.Italic
import com.maximapps.page.data.models.Link
import com.maximapps.page.data.models.Underline
import com.maximapps.page.exceptions.InvalidInlineStyleTypeException
import com.maximapps.page.ext.readFileFromResources
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class InlineStyleDeserializerUnitTest {
    private val type = object : TypeToken<List<InlineStyle>>() {}.type
    private val classLoader = javaClass.classLoader
    private val gson = GsonBuilder()
        .registerTypeAdapter(InlineStyle::class.java, InlineStylesJsonDeserializer())
        .create()

    @Test
    fun `when inline style type is bold then returns a list of styles with a Bold object`() {
        val json = classLoader?.readFileFromResources("inline-styles/chunk.json")
        assertThat(gson.fromJson(json, type), `is`(listOf(Bold(start = 0, end = 5))))
    }

    @Test
    fun `when inline style type is italic then returns a list of styles with an Italic object`() {
        val json = classLoader?.readFileFromResources("inline-styles/chunk2.json")
        assertThat(gson.fromJson(json, type), `is`(listOf(Italic(start = 0, end = 5))))
    }

    @Test
    fun `when inline style type is underline then returns a list of styles with an Underline object`() {
        val json = classLoader?.readFileFromResources("inline-styles/chunk3.json")
        assertThat(gson.fromJson(json, type), `is`(listOf(Underline(start = 0, end = 5))))
    }

    @Test
    fun `when inline style type is link then returns a list of styles with a Link object`() {
        val json = classLoader?.readFileFromResources("inline-styles/chunk4.json")
        assertThat(gson.fromJson(json, type), `is`(listOf(Link(start = 0, end = 5, href = ""))))
    }

    @Test
    fun `when inline style is not empty then returns a list of styles`() {
        val json = classLoader?.readFileFromResources("inline-styles/chunk5.json")
        val expected = listOf(Bold(0, 5), Italic(0, 5), Underline(0, 5))
        assertThat(gson.fromJson(json, type), `is`(expected))
    }

    @Test
    fun `when inline style type is invalid then throws an exception`() {
        val json = classLoader?.readFileFromResources("inline-styles/chunk6.json")
        assertThrows(InvalidInlineStyleTypeException::class.java) {
            gson.fromJson(json, type)
        }
    }
}
