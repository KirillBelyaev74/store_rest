//package ru.store.store_rest.config
//
//import io.grpc.ManagedChannelBuilder
//import ru.store.store_user.UserServiceGrpc
//
//object UserConfig {
//
//    fun getStub(): UserServiceGrpc.UserServiceFutureStub {
//        val channel = ManagedChannelBuilder
//            .forAddress("localhost", 9092)
//            .usePlaintext()
//            .build()
//        return UserServiceGrpc.newFutureStub(channel)
//    }
//}