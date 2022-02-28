package ru.store.store_rest.grpc.mapper

import ru.store.store_rest.model.UserDto
import ru.store.store_user.UserOuterClass

object UserGrpcMapperResponse {

    fun userMapper(user: UserOuterClass.User): UserDto {
        return UserDto(
            id = if (user.hasId()) user.id.value else null,
            role = if (user.hasRole()) user.role.value else null,
            login = if (user.hasLogin()) user.login.value else null,
            password = if (user.hasPassword()) user.password.value else null
        )
    }
}