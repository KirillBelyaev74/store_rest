package ru.store.store_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableCaching
@EnableAsync
class StoreRestApplication

fun main(args: Array<String>) {
	runApplication<StoreRestApplication>(*args)
}

fun String.dropNamePackage(): String {
	return this.drop(this.lastIndexOf(".") + 1)
}

typealias Low = Long
typealias High = Long