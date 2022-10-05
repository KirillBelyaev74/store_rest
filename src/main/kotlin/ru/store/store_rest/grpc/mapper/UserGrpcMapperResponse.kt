package ru.store.store_rest.grpc.mapper

import ru.store.store_rest.config.RoleDto
import ru.store.store_rest.config.UserDto
import ru.store.store_user.UserOuterClass

object UserGrpcMapperResponse {

    fun userMapper(user: UserOuterClass.User): UserDto {
        return UserDto(
            user.login.value,
            user.password.value,
            user.rolesList.map { RoleDto(it.value) }
                .toSet()
        )
    }
}