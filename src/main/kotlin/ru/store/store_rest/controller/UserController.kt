package ru.store.store_rest.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.store.store_rest.config.UserDto
import ru.store.store_rest.service.IUserService

@RestController
class UserController(private val service: IUserService): IUserController {

    override fun saveUser(@RequestBody user: UserDto): String? {
        return service.saveUser(user)
    }

    override fun delete(@PathVariable login: String): String {
        return service.delete(login)
    }
}