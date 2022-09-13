package ru.store.store_rest.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice(annotations = [RestController::class])
class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handlerException(e: Exception): ResponseEntity<Map<String, String?>> {
        val response = mapOf("error" to e.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}