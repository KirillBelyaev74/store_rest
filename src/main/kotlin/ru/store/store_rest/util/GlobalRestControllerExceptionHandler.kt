package ru.store.store_rest.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.LoggingEvent
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(annotations = [RestController::class])
class GlobalRestControllerExceptionHandler(
    private val eventPublisher: ApplicationEventPublisher,
    private val request: HttpServletRequest
) {

    @Value("\${spring.kafka.topic.name}")
    private lateinit var projectName: String

    @ExceptionHandler(Exception::class)
    fun handlerException(e: Exception): ResponseEntity<Map<String, String?>> {
        println(e.stackTraceToString())
        val log = Logging(
            projectName = projectName,
            url = request.requestURI,
            methodName = request.method,
            error = e.message
        )
        eventPublisher.publishEvent(LoggingEvent(this, log))
        val response = mapOf("error" to e.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}