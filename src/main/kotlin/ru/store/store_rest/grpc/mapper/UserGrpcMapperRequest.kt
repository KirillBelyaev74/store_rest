package ru.store.store_rest.grpc.mapper

import com.google.protobuf.StringValue
import ru.store.store_rest.model.UserDto
import ru.store.store_user.UserOuterClass.User

object UserGrpcMapperRequest {

    fun userMapper(user: UserDto): User {
        return User.newBuilder().apply {
            user.username?.let { login = StringValue.of(it) }
            user.password?.let { password = StringValue.of(it) }
            user.roles?.let { addAllRoles(it.map { role -> StringValue.of(role.name) }) }
        }.build()
    }
}