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

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.maximapps.home.data.network.QueryProvider
import javax.inject.Inject

/**
 * Firestore implementation of the [QueryProvider]
 *
 * @since 0.1
 */
class FirestoreQueryProvider @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val config: QueryConfig
): QueryProvider {

    override fun query(): Query = firestore.collection(config.collection)
        .orderBy(config.orderBy, Query.Direction.ASCENDING)
        .limit(config.limit)

    override fun query(startAfter: DocumentSnapshot): Query = firestore
        .collection(config.collection)
        .orderBy(config.orderBy, Query.Direction.ASCENDING)
        .startAfter(startAfter)
        .limit(config.limit)
}
