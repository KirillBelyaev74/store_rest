package ru.store.store_rest.grpc.repository

import org.springframework.stereotype.Repository
import ru.store.store_thing.ThingOuterClass.Status
import ru.store.store_rest.config.GrpcConfig
import ru.store.store_rest.grpc.mapper.ThingGrpcMapperRequest
import ru.store.store_rest.grpc.mapper.ThingGrpcMapperResponse
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

@Repository
open class ThingRepositoryGrpc {

    fun save(thing: ThingDto): Boolean {
        val request = ThingGrpcMapperRequest.thingMapper(thing)
        val response = GrpcConfig.getStub().saveThing(request)
        val result = response.get()
        return when (result.status) {
            Status.OK -> true
            Status.BAD -> false
            else -> throw Exception("${result.error.class_}: ${result.error.message}")
        }
    }

    fun getAllThings(): List<ThingDto> {
        val request = ThingGrpcMapperRequest.requestMapper(null, null)
        val response = GrpcConfig.getStub().getAllThings(request)
        val result = response.get()
        return when (result.status) {
            Status.OK -> result.thingList.map { ThingGrpcMapperResponse.thingDtoMapper(it) }
            else -> throw Exception("${result.error.class_}: ${result.error.message}")
        }
    }

    fun getAllBrandCategorySize(name: String): List<BrandCategorySizeDto> {
        val request = ThingGrpcMapperRequest.requestMapper(name, null)
        val response = GrpcConfig.getStub().getAllBrandCategorySizeResponse(request)
        val result = response.get()
        return when (result.status) {
            Status.OK -> ThingGrpcMapperResponse.brandCategorySizeListMapper(result.brandCategorySizeList)
            else -> throw Exception("${result.error.class_}: ${result.error.message}")
        }
    }

    fun getAllThingsByBrandCategorySize(name: String, value: String): List<ThingDto> {
        val request = ThingGrpcMapperRequest.requestMapper(name, value)
        val response = GrpcConfig.getStub().getAllThingsByBrandCategorySize(request)
        val result = response.get()
        return when (result.status) {
            Status.OK -> result.thingList.map { ThingGrpcMapperResponse.thingDtoMapper(it) }
            else -> throw Exception("${result.error.class_}: ${result.error.message}")
        }
    }

    fun getAllThingsByMiddlePrice(low: Long, high: Long): List<ThingDto> {
        val request = ThingGrpcMapperRequest.requestByMiddlePriceMapper(low, high)
        val response = GrpcConfig.getStub().getAllThingsByMiddlePrice(request)
        val result = response.get()
        return when (result.status) {
            Status.OK -> result.thingList.map { ThingGrpcMapperResponse.thingDtoMapper(it) }
            else -> throw Exception("${result.error.class_}: ${result.error.message}")
        }
    }

    fun deleteById(id: Long): Boolean {
        val request = ThingGrpcMapperRequest.thingMapper(ThingDto(id))
        val response = GrpcConfig.getStub().deleteThingById(request)
        val result = response.get()
        return when (result.status) {
            Status.OK -> true
            Status.BAD -> false
            else -> throw Exception("${result.error.class_}: ${result.error.message}")
        }
    }
}