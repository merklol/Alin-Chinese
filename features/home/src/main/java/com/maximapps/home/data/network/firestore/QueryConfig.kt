package com.maximapps.home.data.network.firestore

/**
 * Configuration for the QueryProvider
 *
 * @since 0.1
 */
class QueryConfig(
    val collection: String = "lessons",
    val orderBy: String = "id",
    val limit: Long = 10,
)
