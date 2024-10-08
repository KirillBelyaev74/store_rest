package ru.store.store_rest.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.logging.annotation.Log
import ru.store.store_rest.model.UserDto
import ru.store.store_rest.service.IUserService

@RestController
open class UserController(private val service: IUserService) : IUserController {

    @Log
    override fun saveUser(@RequestBody user: UserDto): Boolean {
        return service.saveUser(user)
    }

    @Log
    override fun delete(@PathVariable login: String): Boolean {
        return service.delete(login)
    }
}