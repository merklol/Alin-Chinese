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
import com.maximapps.page.data.models.Blockquote
import com.maximapps.page.data.models.Divider
import com.maximapps.page.data.models.Header1
import com.maximapps.page.data.models.Header2
import com.maximapps.page.data.models.Header3
import com.maximapps.page.data.models.Image
import com.maximapps.page.data.models.Node
import com.maximapps.page.data.models.Paragraph
import com.maximapps.page.data.models.UnorderedList
import com.maximapps.page.exceptions.InvalidNodeTypeException
import java.lang.reflect.Type

/**
 * Implementation of the JsonDeserializer interface for converting
 * a jsonElement to the Node model.
 *
 * @since 0.1
 */
internal class NodeJsonDeserializer : JsonDeserializer<Node> {
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): Node =
        when (json.asJsonObject["type"].asString) {
            "header1" -> context.deserialize(json, Header1::class.java)
            "header2" -> context.deserialize(json, Header2::class.java)
            "header3" -> context.deserialize(json, Header3::class.java)
            "paragraph" -> context.deserialize(json, Paragraph::class.java)
            "img" -> context.deserialize(json, Image::class.java)
            "divider" -> context.deserialize(json, Divider::class.java)
            "blockquote" -> context.deserialize(json, Blockquote::class.java)
            "list" -> context.deserialize(json, UnorderedList::class.java)
            else -> throw InvalidNodeTypeException("Unknown node type")
        }
}
