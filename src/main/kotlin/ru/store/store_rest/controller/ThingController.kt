package ru.store.store_rest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto
import ru.store.store_rest.service.IThingService

@RestController
class ThingController(private val service: IThingService): IThingController {

    override fun saveThing(@RequestBody thing: ThingDto): ResponseEntity<Boolean> {
        val result = service.save(thing)
        return when (result) {
            true -> ResponseEntity(result, HttpStatus.OK)
            else -> ResponseEntity(result, HttpStatus.NOT_EXTENDED)
        }
    }

    override fun getAllThings(): List<ThingDto> {
        return service.findAllThings();
    }

    override fun getAllCategory(): List<BrandCategorySizeDto> {
        return service.findAllCategory()
    }

    override fun getAllBrand(): List<BrandCategorySizeDto> {
        return service.findAllBrand()
    }

    override fun getAllSize(): List<BrandCategorySizeDto> {
        return service.findAllSize()
    }

    override fun getAllThingsByCategory(@PathVariable category: String): List<ThingDto> {
        return service.findAllThingsByCategory(category);
    }

    override fun getAllThingsByBrand(@PathVariable brand: String): List<ThingDto> {
        return service.findAllThingsByBrand(brand)
    }

    override fun getAllThingsBySize(@PathVariable size: String): List<ThingDto> {
        return service.findAllThingsBySize(size)
    }

    override fun getAllThingsByMiddlePrice(@PathVariable low: Long, @PathVariable high: Long): List<ThingDto> {
        return service.findAllThingsByMiddlePrice(low, high);
    }

    override fun deleteById(@PathVariable id: Long): ResponseEntity<Boolean> {
        val result = service.deleteById(id);
        return when (result) {
            true -> ResponseEntity(result, HttpStatus.OK)
            else -> ResponseEntity(result, HttpStatus.NOT_EXTENDED)
        }
    }
}