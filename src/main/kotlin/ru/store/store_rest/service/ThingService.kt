package ru.store.store_rest.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import ru.logging.annotation.Log
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto
import ru.store.store_rest.repository.IThingRepository
import ru.store.store_rest.utils.High
import ru.store.store_rest.utils.Low

@Service
open class ThingService(private val repository: IThingRepository) : IThingService {

    @Log
    @CacheEvict(allEntries = true, condition = "#result != null")
    override fun save(thing: ThingDto): Boolean {
        if (thing.category.isNullOrBlank() ||
            thing.brand.isNullOrBlank() ||
            thing.size.isNullOrBlank() ||
            thing.price.toString().isBlank() ||
            thing.description.isNullOrBlank()
        ) {
            throw IllegalArgumentException("Parameters is null or is blank: $thing")
        }
        return repository.save(thing)
    }

    @Log
    @Cacheable(cacheNames = ["findAllThings"], value = ["findAllThings"], unless = "#result.size >= 10")
    override fun findAllThings(): MutableList<ThingDto>? {
        return repository.findAllThings()
    }

    @Log
    @Cacheable(cacheNames = ["findAllCategory"], value = ["findAllCategory"], unless = "#result.size >= 20")
    override fun findAllCategory(): MutableList<BrandCategorySizeDto>? {
        return repository.findAllCategory()
    }

    @Log
    @Cacheable(cacheNames = ["findAllBrand"], value = ["findAllBrand"], unless = "#result.size >= 20")
    override fun findAllBrand(): MutableList<BrandCategorySizeDto>? {
        return repository.findAllBrand()
    }

    @Log
    @Cacheable(cacheNames = ["findAllSize"], value = ["findAllSize"], unless = "#result.size >= 20")
    override fun findAllSize(): MutableList<BrandCategorySizeDto>? {
        return repository.findAllSize()
    }

    @Log
    @Cacheable(
        cacheNames = ["findAllThingsByCategory"],
        value = ["findAllThingsByCategory"],
        unless = "#result.size >= 20",
        key = "#category"
    )
    override fun findAllThingsByCategory(category: String): MutableList<ThingDto>? {
        return repository.findAllThingsByCategory(category)
    }

    @Log
    @Cacheable(
        cacheNames = ["findAllThingsByBrand"],
        value = ["findAllThingsByBrand"],
        unless = "#result.size >= 20",
        key = "#brand"
    )
    override fun findAllThingsByBrand(brand: String): MutableList<ThingDto>? {
        return repository.findAllThingsByBrand(brand)
    }

    @Log
    @Cacheable(
        cacheNames = ["findAllThingsBySize"],
        value = ["findAllThingsBySize"],
        unless = "#result.size >= 20",
        key = "#size"
    )
    override fun findAllThingsBySize(size: String): MutableList<ThingDto>? {
        return repository.findAllThingsBySize(size)
    }

    @Log
    override fun findAllThingsByMiddlePrice(low: Low, high: High): MutableList<ThingDto>? {
        if (low > high) {
            throw IllegalArgumentException("Incorrect parameters value low: $low more that high: $high")
        }
        return repository.findAllThingsByMiddlePrice(low, high)
    }

    @Log
    @CacheEvict(allEntries = true)
    override fun deleteById(id: Long): Boolean {
        if (id < 1) {
            throw IllegalArgumentException("Not correct this id: $id")
        }
        return repository.deleteById(id)
    }
}