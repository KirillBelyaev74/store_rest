package ru.store.store_rest.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import ru.logging.annotation.Log
import ru.store.store_rest.High
import ru.store.store_rest.Low
import ru.store.store_rest.grpc.repository.ThingRepositoryGrpc
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

@Service
open class ThingService(private val repository: ThingRepositoryGrpc): IThingService {

    @Log
    @CacheEvict(allEntries = true, condition = "#result != null")
    override fun save(thing: ThingDto): Boolean {
        if (thing.category.isNullOrBlank() ||
            thing.brand.isNullOrBlank() ||
            thing.size.isNullOrBlank() ||
            thing.price.toString().isBlank() ||
            thing.description.isNullOrBlank()) {
            throw IllegalArgumentException("Parameters is null or is blank: $thing")
        }
        return repository.save(thing)
    }

    @Log
    @Cacheable(cacheNames = ["findAllThings"], value = ["findAllThings"], unless = "#result.size >= 10")
    override fun findAllThings(): List<ThingDto> {
        return repository.getAllThings()
    }

    @Log
    @Cacheable(cacheNames = ["findAllCategory"], value = ["findAllCategory"], unless = "#result.size >= 20")
    override fun findAllCategory(): List<BrandCategorySizeDto> {
        return repository.getAllBrandCategorySize("category")
    }

    @Log
    @Cacheable(cacheNames = ["findAllBrand"], value = ["findAllBrand"], unless = "#result.size >= 20")
    override fun findAllBrand(): List<BrandCategorySizeDto> {
        return repository.getAllBrandCategorySize("brand")
    }

    @Log
    @Cacheable(cacheNames = ["findAllSize"], value = ["findAllSize"], unless = "#result.size >= 20")
    override fun findAllSize(): List<BrandCategorySizeDto> {
        return repository.getAllBrandCategorySize("size")
    }

    @Log
    @Cacheable(cacheNames = ["findAllThingsByCategory"], value = ["findAllThingsByCategory"], unless = "#result.size >= 20", key = "#category")
    override fun findAllThingsByCategory(category: String): List<ThingDto> {
        return repository.getAllThingsByBrandCategorySize("category", category)
    }

    @Log
    @Cacheable(cacheNames = ["findAllThingsByBrand"], value = ["findAllThingsByBrand"], unless = "#result.size >= 20", key = "#brand")
    override fun findAllThingsByBrand(brand: String): List<ThingDto> {
        return repository.getAllThingsByBrandCategorySize("brand", brand)
    }

    @Log
    @Cacheable(cacheNames = ["findAllThingsBySize"], value = ["findAllThingsBySize"], unless = "#result.size >= 20", key = "#size")
    override fun findAllThingsBySize(size: String): List<ThingDto> {
        return repository.getAllThingsByBrandCategorySize("size", size)
    }

    @Log
    override fun findAllThingsByMiddlePrice(low: Low, high: High): List<ThingDto> {
        if (low > high) {
            throw IllegalArgumentException("Incorrect parameters value low: $low more that high: $high")
        }
        return repository.getAllThingsByMiddlePrice(low, high)
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