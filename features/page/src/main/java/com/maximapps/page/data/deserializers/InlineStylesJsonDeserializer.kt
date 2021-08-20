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

package com.maximapps.page.data.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.maximapps.page.data.models.Bold
import com.maximapps.page.data.models.InlineStyle
import com.maximapps.page.data.models.Italic
import com.maximapps.page.data.models.Link
import com.maximapps.page.data.models.Underline
import com.maximapps.page.exceptions.InvalidInlineStyleTypeException
import java.lang.reflect.Type

/**
 * Implementation of the JsonDeserializer interface for converting
 * a jsonElement to the InlineStyles model.
 *
 * @since 0.1
 */
internal class InlineStylesJsonDeserializer : JsonDeserializer<InlineStyle> {

    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): InlineStyle =
        when (json.asJsonObject["type"].asString) {
            "bold" -> context.deserialize(json, Bold::class.java)
            "italic" -> context.deserialize(json, Italic::class.java)
            "underline" -> context.deserialize(json, Underline::class.java)
            "link" -> context.deserialize(json, Link::class.java)
            else -> throw InvalidInlineStyleTypeException("Unknown inline style type")
        }
}
