package ru.store.store_rest.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.store.store_rest.grpc.repository.UserRepositoryGrpc
import ru.store.store_rest.model.UserDto

@Service
class UserDetailsServiceImpl(private val repository: UserRepositoryGrpc): UserDetailsService {

    fun saveUser(user: UserDto): String? {
        repository.getUserByLogin(user.username).run {
        if (username.isNullOrBlank() || password.isNullOrBlank() || roles.isEmpty()) {
            return "-1"
        } }
        return repository.saveUser(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return repository.getUserByLogin(username)
    }

    fun delete(login: String): String {
        return repository.delete(login)
    }
}