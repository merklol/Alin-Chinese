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

package com.maximapps.homesample.data

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import com.google.firebase.firestore.QuerySnapshot
import com.maximapps.home.data.models.Lesson
import com.maximapps.home.data.network.firestore.exceptions.FirestoreException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.spyk

val pagingSourceErrorResult = spyk<PagingSource<QuerySnapshot, Lesson>>().also {
    every { it.getRefreshKey(any()) } returns null
    coEvery { it.load(any()) } returns LoadResult.Error(FirestoreException)
}

val pagingSourceSuccessResult = spyk<PagingSource<QuerySnapshot, Lesson>>().also {
    every { it.getRefreshKey(any()) } returns null
    coEvery { it.load(any()) } returns LoadResult.Page(
        data = sampleData,
        prevKey = null,
        nextKey = null
    )
}