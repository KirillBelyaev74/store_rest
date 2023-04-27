package ru.store.store_rest.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.logging.annotation.Log
import ru.store.store_rest.grpc.repository.UserRepositoryGrpc
import ru.store.store_rest.model.UserDto

@Service
open class UserDetailsServiceImpl(private val repository: UserRepositoryGrpc) : UserDetailsService {

    @Log
    fun saveUser(user: UserDto): String? {
        repository.getUserByLogin(user.username).run {
            if (username.isNullOrBlank() || password.isNullOrBlank() || roles.isEmpty()) {
                return "-1"
            }
        }
        return repository.saveUser(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return repository.getUserByLogin(username)
    }

    @Log
    fun delete(login: String): String {
        return repository.delete(login)
    }
}