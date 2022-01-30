package ru.store.store_rest.grpc.mapper

import ru.store.store_rest.ThingOuterClass
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

object ThingGrpcMapperResponse {

    fun thingDtoMapper(thing: ThingOuterClass.Thing): ThingDto {
        return ThingDto(
            id = thing.id,
            brand = thing.brand,
            category = thing.category,
            size = thing.size,
            price = thing.price,
            description = thing.description
        )
    }

    fun brandCategorySizeListMapper(list: List<ThingOuterClass.BrandCategorySize>): List<BrandCategorySizeDto> {
        return list.map {
            BrandCategorySizeDto(it.id, it.brandCategorySize)
        }
    }
}