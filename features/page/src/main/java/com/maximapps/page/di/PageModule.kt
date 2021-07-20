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

package com.maximapps.page.di

import com.google.gson.GsonBuilder
import com.maximapps.page.data.ContentParser
import com.maximapps.page.data.deserializers.AlignmentDeserializer
import com.maximapps.page.data.deserializers.InlineStyleJsonDeserializer
import com.maximapps.page.data.deserializers.NodeJsonDeserializer
import com.maximapps.page.data.models.Alignment
import com.maximapps.page.data.models.Node
import com.maximapps.page.data.models.InlineStyle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PageModule {

    @Provides
    @Singleton
    fun providesParser(): ContentParser = ContentParser(
        GsonBuilder()
            .registerTypeAdapter(Node::class.java, NodeJsonDeserializer())
            .registerTypeAdapter(InlineStyle::class.java, InlineStyleJsonDeserializer())
            .registerTypeAdapter(Alignment::class.java, AlignmentDeserializer())
            .create()
    )
}
