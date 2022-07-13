package ru.store.store_rest.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.store.store_rest.model.UserDto

@RequestMapping("/user")
interface IUserController {

    @PostMapping("/")
    fun saveUser(@RequestBody user: UserDto): ResponseEntity<Boolean>

    @GetMapping("/login/{login}")
    fun getUserByLogin(@PathVariable login: String): UserDto

    @GetMapping("/id/{id}")
    fun getUserById(@PathVariable id: Long): UserDto

    @GetMapping("/")
    fun getUser(@RequestBody user: UserDto): UserDto

    @GetMapping("/all")
    fun getAllUsers(): List<UserDto>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Boolean
}