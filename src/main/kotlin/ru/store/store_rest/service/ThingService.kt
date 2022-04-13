package ru.store.store_rest.service

import org.springframework.stereotype.Service
import ru.store.store_rest.grpc.repository.ThingRepositoryGrpc
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

@Service
class ThingService(private val repository: ThingRepositoryGrpc): IThingService {

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

    override fun findAllThings(): List<ThingDto> {
        return repository.getAllThings()
    }

    override fun findAllCategory(): List<BrandCategorySizeDto> {
        return repository.getAllBrandCategorySize("category")
    }

    override fun findAllBrand(): List<BrandCategorySizeDto> {
        return repository.getAllBrandCategorySize("brand")
    }

    override fun findAllSize(): List<BrandCategorySizeDto> {
        return repository.getAllBrandCategorySize("size")
    }

    override fun findAllThingsByCategory(category: String): List<ThingDto> {
        return repository.getAllThingsByBrandCategorySize("category", category)
    }

    override fun findAllThingsByBrand(brand: String): List<ThingDto> {
        return repository.getAllThingsByBrandCategorySize("brand", brand)
    }

    override fun findAllThingsBySize(size: String): List<ThingDto> {
        return repository.getAllThingsByBrandCategorySize("size", size)
    }

    override fun findAllThingsByMiddlePrice(low: Long, high: Long): List<ThingDto> {
        if (low > high) {
            throw IllegalArgumentException("Incorrect parameters value low: $low more that high: $high")
        }
        return repository.getAllThingsByMiddlePrice(low, high)
    }

    override fun deleteById(id: Long): Boolean {
        if (id < 1) {
            throw IllegalArgumentException("Not correct this id: $id")
        }
        return repository.deleteById(id)
    }
}