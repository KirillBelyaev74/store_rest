package ru.store.store_rest.service

import ru.store.store_rest.model.UserDto

interface IUserService {

    fun saveUser(user: UserDto): Boolean

    fun getUserByLogin(login: String): UserDto

    fun getUserById(id: Long): UserDto

    fun getUser(user: UserDto): UserDto

    fun delete(id: Long): Boolean
}