package ru.store.store_rest.controller

import org.springframework.web.bind.annotation.*
import ru.store.store_rest.config.UserDto

@RequestMapping("/user")
interface IUserController {

    @PostMapping("/")
    fun saveUser(@RequestBody user: UserDto): String?

    @DeleteMapping("/{login}")
    fun delete(@PathVariable login: String): String
}