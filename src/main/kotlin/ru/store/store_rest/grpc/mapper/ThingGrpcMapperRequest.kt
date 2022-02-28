package ru.store.store_rest.grpc.mapper

import ru.store.store_rest.ThingOuterClass.*
import ru.store.store_rest.model.RequestGrpcDto
import ru.store.store_rest.model.ThingDto

object ThingGrpcMapperRequest {

    fun thingMapper(thing: ThingDto): Thing {
        return Thing.newBuilder()
            .setId(thing.id)
            .setBrand(thing.brand ?: "")
            .setCategory(thing.category ?: "")
            .setSize(thing.size ?: "")
            .setPrice(thing.price ?: 0)
            .setDescription(thing.description ?: "")
            .build()
    }

    fun requestMapper(request: RequestGrpcDto?): Request {
        return Request.newBuilder()
            .setName(request?.name ?: "")
            .setValue(request?.value ?: "")
            .build()
    }

    fun requestByMiddlePriceMapper(low: Long, high: Long): RequestByMiddlePrice {
        return RequestByMiddlePrice.newBuilder()
            .setLow(low)
            .setHigh(high)
            .build()
    }
}