package ru.store.store_rest.config

import io.grpc.ManagedChannelBuilder
import ru.store.store_rest.ThingServiseGrpc

object GrpcConfig {

    fun getStub(): ThingServiseGrpc.ThingServiseFutureStub {
        val channel = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .usePlaintext()
            .build()
        return ThingServiseGrpc.newFutureStub(channel)
    }
}