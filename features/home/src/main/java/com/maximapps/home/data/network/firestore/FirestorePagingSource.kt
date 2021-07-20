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

package com.maximapps.home.data.network.firestore

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.QuerySnapshot
import com.maximapps.home.data.models.Lesson
import com.maximapps.home.data.models.Source
import com.maximapps.home.data.network.QueryProvider
import com.maximapps.home.data.network.firestore.exceptions.FirestoreException
import com.maximapps.home.data.toLessons
import kotlinx.coroutines.tasks.await

/**
 * Firestore implementation of the [PagingSource].
 *
 * @since 0.1
 */
internal class FirestorePagingSource(private val provider: QueryProvider) :
    PagingSource<QuerySnapshot, Lesson>() {

    override fun getRefreshKey(state: PagingState<QuerySnapshot, Lesson>): QuerySnapshot? = null

    override suspend fun load(params: LoadParams<QuerySnapshot>)
            : LoadResult<QuerySnapshot, Lesson> =
        try {
            val current = getQuerySnapshot(params)
            val lessons = provider.query(current.documents[current.size() - 1]).get().await()

            LoadResult.Page(
                data = current.toObjects(Source::class.java).toLessons(),
                prevKey = null,
                nextKey = if (lessons.isEmpty) null else lessons
            )
        } catch (e: FirestoreException) {
            LoadResult.Error(e)
        }

    private suspend fun getQuerySnapshot(params: LoadParams<QuerySnapshot>): QuerySnapshot {
        val current = params.key ?: provider.query().get().await()
        if (current.isEmpty) {
            throw FirestoreException
        }
        return current
    }
}
