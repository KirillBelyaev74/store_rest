package ru.store.store_rest.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

interface IThingController {

    fun saveThing(@RequestBody thing: ThingDto): ResponseEntity<Boolean>

    fun getAllThings(): List<ThingDto>

    fun getAllCategory(): List<BrandCategorySizeDto>

    fun getAllBrand(): List<BrandCategorySizeDto>

    fun getAllSize(): List<BrandCategorySizeDto>

    fun getAllThingsByCategory(@PathVariable category: String): List<ThingDto>

    fun getAllThingsByBrand(@PathVariable brand: String): List<ThingDto>

    fun getAllThingsBySize(@PathVariable size: String): List<ThingDto>

    fun getAllThingsByMiddlePrice(@PathVariable low: Long, @PathVariable high: Long): List<ThingDto>

    fun deleteById(@PathVariable id: Long): ResponseEntity<Boolean>
}