package ru.store.store_rest.grpc.mapper

import com.google.protobuf.StringValue
import ru.store.store_rest.model.UserDto
import ru.store.store_user.UserOuterClass.User

object UserGrpcMapperRequest {

    fun userMapper(user: UserDto): User {
        return User.newBuilder().apply {
            role = StringValue.of(user.role)
            login = StringValue.of(user.login)
            password = StringValue.of(user.password)
        }.build()
    }
}