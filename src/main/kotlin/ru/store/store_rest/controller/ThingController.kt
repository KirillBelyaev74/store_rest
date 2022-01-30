package ru.store.store_rest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto
import ru.store.store_rest.service.ThingService

@RestController
@RequestMapping("/shop")
class ThingController(private val service: ThingService): IThingController {

    @PostMapping("/")
    override fun saveThing(@RequestBody thing: ThingDto): ResponseEntity<Boolean> {
        val result = service.save(thing)
        return when (result) {
            true -> ResponseEntity(result, HttpStatus.OK)
            else -> ResponseEntity(result, HttpStatus.NOT_EXTENDED)
        }
    }

    @GetMapping("/")
    override fun getAllThings(): List<ThingDto> {
        return service.findAllThings();
    }

    @GetMapping("/category")
    override fun getAllCategory(): List<BrandCategorySizeDto> {
        return service.findAllCategory()
    }

    @GetMapping("/brand")
    override fun getAllBrand(): List<BrandCategorySizeDto> {
        return service.findAllBrand()
    }

    @GetMapping("/size")
    override fun getAllSize(): List<BrandCategorySizeDto> {
        return service.findAllSize()
    }

    @GetMapping("/category/{category}")
    override fun getAllThingsByCategory(@PathVariable category: String): List<ThingDto> {
        return service.findAllThingsByCategory(category);
    }

    @GetMapping("/brand/{brand}")
    override fun getAllThingsByBrand(@PathVariable brand: String): List<ThingDto> {
        return service.findAllThingsByBrand(brand)
    }

    @GetMapping("/size/{size}")
    override fun getAllThingsBySize(@PathVariable size: String): List<ThingDto> {
        return service.findAllThingsBySize(size)
    }

    @GetMapping("/{low}/{high}")
    override fun getAllThingsByMiddlePrice(@PathVariable low: Long, @PathVariable high: Long): List<ThingDto> {
        return service.findAllThingsByMiddlePrice(low, high);
    }

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long): ResponseEntity<Boolean> {
        val result = service.deleteById(id);
        return when (result) {
            true -> ResponseEntity(result, HttpStatus.OK)
            else -> ResponseEntity(result, HttpStatus.NOT_EXTENDED)
        }
    }
}