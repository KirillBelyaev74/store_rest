package ru.store.store_rest.service

import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

interface IThingService {

    fun save(thing: ThingDto): Boolean

    fun findAllThings(): List<ThingDto>

    fun findAllCategory(): List<BrandCategorySizeDto>

    fun findAllBrand(): List<BrandCategorySizeDto>

    fun findAllSize(): List<BrandCategorySizeDto>

    fun findAllThingsByCategory(category: String): List<ThingDto>

    fun findAllThingsByBrand(brand: String): List<ThingDto>

    fun findAllThingsBySize(size: String): List<ThingDto>

    fun findAllThingsByMiddlePrice(low: Long, high: Long): List<ThingDto>

    fun deleteById(id: Long): Boolean
}