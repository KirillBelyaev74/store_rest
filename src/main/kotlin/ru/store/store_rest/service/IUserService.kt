package ru.store.store_rest.service

import ru.store.store_rest.config.UserDto

interface IUserService {

    fun saveUser(user: UserDto): String?

    fun delete(login: String): String
}