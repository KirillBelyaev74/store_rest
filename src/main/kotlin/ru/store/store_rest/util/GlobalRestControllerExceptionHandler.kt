package ru.store.store_rest.util

import com.fasterxml.jackson.databind.ObjectMapper
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

    @ExceptionHandler(Exception::class)
    fun handlerException(e: Exception): ResponseEntity<Map<String, String?>> {
        val response = mapOf("error" to e.message)

        val logging = Logging(
            projectName = "store_rest",
            url = request.requestURI,
            methodName = request.method,
            error = e.message
        )
        eventPublisher.publishEvent(LoggingEvent(this, logging))

        println(e.stackTraceToString())
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}