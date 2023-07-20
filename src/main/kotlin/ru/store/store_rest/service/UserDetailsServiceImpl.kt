package ru.store.store_rest.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.logging.annotation.Log
import ru.store.store_rest.model.UserDto
import ru.store.store_rest.repository.IUserRepository

@Service
open class UserDetailsServiceImpl(private val repository: IUserRepository) : UserDetailsService {

    @Log
    fun saveUser(user: UserDto): Boolean {
        return repository.saveUser(user)
    }

    override fun loadUserByUsername(username: String): UserDto? {
        return repository.getUserByLogin(username)
    }

    @Log
    fun delete(login: String): Boolean {
        return repository.delete(login)
    }
}