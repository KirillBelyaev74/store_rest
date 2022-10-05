package ru.store.store_rest.service

import ru.store.store_rest.model.UserDto

interface IUserService {

    fun saveUser(user: UserDto): String?

    fun delete(login: String): String
}