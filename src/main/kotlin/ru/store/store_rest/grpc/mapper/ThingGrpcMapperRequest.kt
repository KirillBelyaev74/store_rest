package ru.store.store_rest.grpc.mapper

import com.google.protobuf.Int64Value
import com.google.protobuf.StringValue
import ru.store.store_rest.model.ThingDto
import ru.store.store_thing.ThingOuterClass.*

object ThingGrpcMapperRequest {

    fun thingMapper(thing: ThingDto): Thing {
        return Thing.newBuilder().apply {
            thing.id?.let { id = Int64Value.of(it) }
            thing.brand?.let { brand = StringValue.of(it) }
            thing.category?.let { category = StringValue.of(it) }
            thing.size?.let { size = StringValue.of(it) }
            thing.price?.let { price = Int64Value.of(it) }
            thing.description?.let { description = StringValue.of(it) }
        }.build()
    }

    fun requestMapper(name: String?, value: String?): Request {
        return Request.newBuilder().apply {
            name?.let { this.name = StringValue.of(it) }
            value?.let { this.value = StringValue.of(it) }
        }.build()
    }

    fun requestByMiddlePriceMapper(low: Long, high: Long): RequestByMiddlePrice {
        return RequestByMiddlePrice.newBuilder()
            .setLow(low)
            .setHigh(high)
            .build()
    }
}