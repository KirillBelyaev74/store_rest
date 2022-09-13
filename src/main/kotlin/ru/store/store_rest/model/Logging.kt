package ru.store.store_rest.model

data class Logging(
    val projectName: String,
    val className: String,
    val methodName: String,
    val typeMessage: RequestResponse,
    val requestResponse: String? = null,
    val error: String? = null
)