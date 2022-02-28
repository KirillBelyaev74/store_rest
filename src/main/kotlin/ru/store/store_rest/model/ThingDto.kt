package ru.store.store_rest.model

data class ThingDto(
    var id: Long? = null,
    var brand: String? = null,
    var category: String? = null,
    var size: String? = null,
    var price: Long? = null,
    var description: String? = null
)
