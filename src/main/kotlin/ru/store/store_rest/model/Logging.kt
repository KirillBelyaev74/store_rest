package ru.store.store_rest.model

data class Logging(
    val projectName: String,
    val className: String? = null,
    val methodName: String? = null,
    val typeMessage: RequestResponse? = null,
    val requestResponse: String? = null,
    val error: String? = null
)