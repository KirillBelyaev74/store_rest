package ru.store.store_rest.service

import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto
import ru.store.store_rest.utils.High
import ru.store.store_rest.utils.Low

interface IThingService {

    fun save(thing: ThingDto): Boolean

    fun findAllThings(): MutableList<ThingDto>?

    fun findAllCategory(): MutableList<BrandCategorySizeDto>?

    fun findAllBrand(): MutableList<BrandCategorySizeDto>?

    fun findAllSize(): MutableList<BrandCategorySizeDto>?

    fun findAllThingsByCategory(category: String): MutableList<ThingDto>?

    fun findAllThingsByBrand(brand: String): MutableList<ThingDto>?

    fun findAllThingsBySize(size: String): MutableList<ThingDto>?

    fun findAllThingsByMiddlePrice(low: Low, high: High): MutableList<ThingDto>?

    fun deleteById(id: Long): Boolean
}