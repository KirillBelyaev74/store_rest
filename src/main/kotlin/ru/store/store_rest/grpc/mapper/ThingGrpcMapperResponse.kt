//package ru.store.store_rest.grpc.mapper
//
//import ru.store.store_rest.model.BrandCategorySizeDto
//import ru.store.store_rest.model.ThingDto
//import ru.store.store_thing.ThingOuterClass
//
//object ThingGrpcMapperResponse {
//
//    fun thingDtoMapper(thing: ThingOuterClass.Thing): ThingDto {
//        return ThingDto(
//            id = if (thing.hasId()) thing.id.value else null,
//            brand = if (thing.hasBrand()) thing.brand.value else null,
//            category = if (thing.hasCategory()) thing.category.value else null,
//            size = if (thing.hasSize()) thing.size.value else null,
//            price = if (thing.hasPrice()) thing.price.value else null,
//            description = if (thing.hasDescription()) thing.description.value else null
//        )
//    }
//
//    fun brandCategorySizeListMapper(list: List<ThingOuterClass.BrandCategorySize>): List<BrandCategorySizeDto> {
//        return list.map {
//            BrandCategorySizeDto(
//                id = if (it.hasId()) it.id.value else null,
//                brandCategorySize = if (it.hasBrandCategorySize()) it.brandCategorySize.value else null
//            )
//        }
//    }
//}