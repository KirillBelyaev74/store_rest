package ru.store.store_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class StoreRestApplication

fun main(args: Array<String>) {
	runApplication<StoreRestApplication>(*args)
}
