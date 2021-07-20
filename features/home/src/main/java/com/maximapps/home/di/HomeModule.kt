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

package com.maximapps.home.di

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maximapps.home.data.models.Lesson
import com.maximapps.home.data.network.QueryProvider
import com.maximapps.home.data.network.firestore.FirestorePagingSource
import com.maximapps.home.data.network.firestore.FirestoreQueryProvider
import com.maximapps.home.data.network.firestore.QueryConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun providesPagingConfig(): PagingConfig =
        PagingConfig(pageSize = 10, enablePlaceholders = false)

    @Singleton
    @Provides
    fun providesLessonProvider(): QueryProvider = FirestoreQueryProvider(
        Firebase.firestore, QueryConfig()
    )

    @Singleton
    @Provides
    fun providesFirestorePagingSource(queryProvider: QueryProvider)
            : PagingSource<QuerySnapshot, Lesson> =
        FirestorePagingSource(queryProvider)
}
