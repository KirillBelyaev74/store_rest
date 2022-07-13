package ru.store.store_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
open class StoreRestApplication

fun main(args: Array<String>) {
	runApplication<StoreRestApplication>(*args)
}
