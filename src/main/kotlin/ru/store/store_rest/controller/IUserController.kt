package ru.store.store_rest.controller

import org.springframework.web.bind.annotation.*
import ru.store.store_rest.model.UserDto

@RequestMapping("/user")
interface IUserController {

    @PostMapping("/")
    fun saveUser(@RequestBody user: UserDto): Boolean

    @DeleteMapping("/{login}")
    fun delete(@PathVariable login: String): Boolean
}