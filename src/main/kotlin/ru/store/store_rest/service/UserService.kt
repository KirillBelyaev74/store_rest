package ru.store.store_rest.service

import org.springframework.stereotype.Service
import ru.store.store_rest.config.UserDto

@Service
class UserService(private val service: UserDetailsServiceImpl): IUserService {

    override fun saveUser(user: UserDto): String? {
        user.run {
            if (username.isNullOrBlank() || password.isNullOrBlank() || roles.isEmpty()) {
                throw IllegalArgumentException("Not correct parameters login: [${user.username}] or password or role: [${user.roles}]")
            }
        }
        return service.saveUser(user)
    }

    override fun delete(login: String): String {
        if (login.isNullOrBlank()) {
            throw IllegalArgumentException("Not correct parameters login: [$login]")
        }
        return service.delete(login)
    }
}