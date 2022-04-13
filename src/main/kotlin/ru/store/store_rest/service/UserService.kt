package ru.store.store_rest.service

import org.springframework.stereotype.Service
import ru.store.store_rest.grpc.repository.UserRepositoryGrpc
import ru.store.store_rest.model.UserDto

@Service
class UserService(private val repository: UserRepositoryGrpc): IUserService {

    override fun saveUser(user: UserDto): Boolean {
        user.run {
            if (login.isNullOrBlank() || password.isNullOrBlank() || role.isNullOrBlank()) {
                throw IllegalArgumentException("Not correct parameters login: [$login] or password: [$password] or role: [$role]")
            }
        }
        return repository.saveUser(user)
    }

    override fun getUserByLogin(login: String): UserDto {
        if (login.isNullOrBlank()) {
            throw IllegalArgumentException("Not correct parameters login: [$login]")
        }
        return repository.getUserByLogin(login)
    }

    override fun getUserById(id: Long): UserDto {
        if (id == null || id < 1) {
            throw IllegalArgumentException("Not correct parameters id: [$id]")
        }
        return repository.getUserById(id)
    }

    override fun getUser(user: UserDto): UserDto {
        user.run {
            if (login.isNullOrBlank() || password.isNullOrBlank() || role.isNullOrBlank()) {
                throw IllegalArgumentException("Not correct parameters login: [$login] or password: [$password] or role: [$role]")
            }
        }
        return repository.getUser(user)
    }

    override fun delete(id: Long): Boolean {
        if (id == null || id < 1) {
            throw IllegalArgumentException("Not correct parameters id: [$id]")
        }
        return repository.delete(id)
    }
}