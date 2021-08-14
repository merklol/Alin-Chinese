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

package com.maximapps.core.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Parcelable data class for Safe Args.
 *
 * @since 0.1
 */
@Parcelize
data class SharedLesson(
    val id: Int,
    val primaryTitle: String,
    val secondaryTitle: String,
    val thumbnail: ByteArray,
    val description: String,
    val body: String,
    val date: String
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SharedLesson

        if (id != other.id) return false
        if (primaryTitle != other.primaryTitle) return false
        if (secondaryTitle != other.secondaryTitle) return false
        if (description != other.description) return false
        if (body != other.body) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + primaryTitle.hashCode()
        result = 31 * result + secondaryTitle.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + thumbnail.contentHashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}
