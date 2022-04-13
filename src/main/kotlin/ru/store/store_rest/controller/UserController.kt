package ru.store.store_rest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.store.store_rest.model.UserDto
import ru.store.store_rest.service.IUserService

@RestController
class UserController(private val service: IUserService): IUserController {

    override fun saveUser(@RequestBody user: UserDto): ResponseEntity<Boolean> {
        val result = service.saveUser(user)
        return when (result) {
            true -> ResponseEntity(result, HttpStatus.OK)
            else -> ResponseEntity(result, HttpStatus.NOT_EXTENDED)
        }
    }

    override fun getUserByLogin(@PathVariable login: String): UserDto {
        return service.getUserByLogin(login)
    }

    override fun getUserById(@PathVariable id: Long): UserDto {
        return service.getUserById(id)
    }

    override fun getUser(@RequestBody user: UserDto): UserDto {
        return service.getUser(user)
    }

    override fun delete(@PathVariable id: Long): Boolean {
        return service.delete(id)
    }
}