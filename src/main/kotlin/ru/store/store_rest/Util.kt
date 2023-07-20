package ru.store.store_rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

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