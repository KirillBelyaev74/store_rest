package ru.store.store_rest.model

import ru.logging.annotation.LogFieldSkip

data class BrandCategorySizeDto(
    @LogFieldSkip
    val id: Long? = null,
    val brandCategorySize: String? = null
)
