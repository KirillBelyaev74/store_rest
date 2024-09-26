package ru.store.store_rest.repository

import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import ru.store.store_rest.config.EndpointsConfig
import ru.store.store_rest.model.UserDto
import ru.store.store_rest.utils.Uri
import ru.store.store_rest.utils.deleteMono
import ru.store.store_rest.utils.getMono
import ru.store.store_rest.utils.postMono

@Repository
open class UserRepository(private val webClient: WebClient, private val endpoints: EndpointsConfig) : IUserRepository {

    override fun saveUser(user: UserDto): Boolean {
        val uri = Uri.uriUser(endpoints = endpoints)
        val response = webClient.postMono(uri, Int::class.java, user)
        return when (response) {
            1 -> true; else -> false
        }
    }

    override fun getUserByLogin(login: String): UserDto? {
        val uri = Uri.uriUser(login, endpoints)
        return webClient.getMono(uri, UserDto::class.java)
    }

    override fun delete(login: String): Boolean {
        val uri = Uri.uriUser(login, endpoints)
        val response = webClient.deleteMono(uri, Int::class.java)
        return when (response) { 1 -> true; else -> false }
    }
}