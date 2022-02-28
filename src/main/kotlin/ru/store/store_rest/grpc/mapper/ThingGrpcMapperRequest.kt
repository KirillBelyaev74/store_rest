package ru.store.store_rest.grpc.mapper

import com.google.protobuf.Int64Value
import com.google.protobuf.StringValue
import ru.store.store_thing.ThingOuterClass.*
import ru.store.store_rest.model.ThingDto

object ThingGrpcMapperRequest {

    fun thingMapper(thing: ThingDto): Thing {
        return Thing.newBuilder().apply {
            id = thing.id?.let { Int64Value.of(it) }
            brand = thing.brand?.let { StringValue.of(it) }
            category = thing.category?.let { StringValue.of(it) }
            size = thing.size?.let { StringValue.of(it) }
            price = thing.price?.let { Int64Value.of(it) }
            description = thing.description?.let { StringValue.of(it) }
        }.build()
    }

    fun requestMapper(name: String?, value: String?): Request {
        return Request.newBuilder()
            .setName(StringValue.of(name))
            .setValue(StringValue.of(value))
            .build()
    }

    fun requestByMiddlePriceMapper(low: Long, high: Long): RequestByMiddlePrice {
        return RequestByMiddlePrice.newBuilder()
            .setLow(low)
            .setHigh(high)
            .build()
    }
}