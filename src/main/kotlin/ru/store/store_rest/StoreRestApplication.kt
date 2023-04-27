package ru.store.store_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableCaching
@EnableAsync
open class StoreRestApplication

fun main(args: Array<String>) {
    runApplication<StoreRestApplication>(*args)
}

fun String.dropNamePackage(): String {
    return this.drop(this.lastIndexOf(".") + 1)
}

typealias Low = Long
typealias High = Long

fun Boolean.checkResponseBD(): ResponseEntity<Boolean> {
    return when (this) {
        true -> ResponseEntity(this, HttpStatus.OK)
        else -> ResponseEntity(this, HttpStatus.NOT_EXTENDED)
    }
}