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
import com.maximapps.page.data.deserializers.AlignmentDeserializer
import com.maximapps.page.data.models.Alignment
import com.maximapps.page.exceptions.InvalidAlignmentTypeException
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class AlignmentDeserializerUnitTest {
    private val gson = GsonBuilder()
        .registerTypeAdapter(Alignment::class.java, AlignmentDeserializer())
        .create()

    @Test
    fun `when alignment type is start then returns Start object`() {
        assertThat(gson.fromJson("start", Alignment::class.java), `is`(Alignment.Start))
    }

    @Test
    fun `when alignment type is center then returns Center object`() {
        assertThat(gson.fromJson("center", Alignment::class.java), `is`(Alignment.Center))
    }

    @Test
    fun `when alignment type is end then returns End object`() {
        assertThat(gson.fromJson("end", Alignment::class.java), `is`(Alignment.End))
    }

    @Test
    fun `when alignment type is invalid then throws an exception`() {
        assertThrows(InvalidAlignmentTypeException::class.java) {
            gson.fromJson("left", Alignment::class.java)
        }
    }
}
