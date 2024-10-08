package ru.store.store_rest.service

import org.springframework.stereotype.Service
import ru.logging.annotation.Log
import ru.store.store_rest.model.UserDto

@Service
open class UserService(private val service: UserDetailsServiceImpl) : IUserService {

    @Log
    override fun saveUser(user: UserDto): Boolean {
        user.run {
            if (username.isNullOrBlank() || password.isNullOrBlank() || roles.isEmpty()) {
                throw IllegalArgumentException("Not correct parameters login: [${user.username}] or password or role: [${user.roles}]")
            }
        }
        return service.saveUser(user)
    }

    @Log
    override fun delete(login: String): Boolean {
        if (login.isNullOrBlank()) {
            throw IllegalArgumentException("Not correct parameters login: [$login]")
        }
        return service.delete(login)
    }
}