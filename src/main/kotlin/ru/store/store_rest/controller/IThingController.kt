package ru.store.store_rest.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

@RequestMapping("/thing")
interface IThingController {

    @PostMapping("/")
    fun saveThing(@RequestBody thing: ThingDto): ResponseEntity<Boolean>

    @GetMapping("/")
    fun getAllThings(): List<ThingDto>

    @GetMapping("/category")
    fun getAllCategory(): List<BrandCategorySizeDto>

    @GetMapping("/brand")
    fun getAllBrand(): List<BrandCategorySizeDto>

    @GetMapping("/size")
    fun getAllSize(): List<BrandCategorySizeDto>

    @GetMapping("/category/{category}")
    fun getAllThingsByCategory(@PathVariable category: String): List<ThingDto>

    @GetMapping("/brand/{brand}")
    fun getAllThingsByBrand(@PathVariable brand: String): List<ThingDto>

    @GetMapping("/size/{size}")
    fun getAllThingsBySize(@PathVariable size: String): List<ThingDto>

    @GetMapping("/{low}/{high}")
    fun getAllThingsByMiddlePrice(@PathVariable low: Long, @PathVariable high: Long): List<ThingDto>

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Boolean>
}