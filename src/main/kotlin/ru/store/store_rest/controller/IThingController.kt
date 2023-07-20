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
    fun getAllThings(): MutableList<ThingDto>?

    @GetMapping("/category")
    fun getAllCategory(): MutableList<BrandCategorySizeDto>?

    @GetMapping("/brand")
    fun getAllBrand(): MutableList<BrandCategorySizeDto>?

    @GetMapping("/size")
    fun getAllSize(): MutableList<BrandCategorySizeDto>?

    @GetMapping("/category/{category}")
    fun getAllThingsByCategory(@PathVariable category: String): MutableList<ThingDto>?

    @GetMapping("/brand/{brand}")
    fun getAllThingsByBrand(@PathVariable brand: String): MutableList<ThingDto>?

    @GetMapping("/size/{size}")
    fun getAllThingsBySize(@PathVariable size: String): MutableList<ThingDto>?

    @GetMapping("/{low}/{high}")
    fun getAllThingsByMiddlePrice(@PathVariable low: Long, @PathVariable high: Long): MutableList<ThingDto>?

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Boolean>
}