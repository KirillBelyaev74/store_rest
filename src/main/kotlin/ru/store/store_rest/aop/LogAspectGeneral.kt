package ru.store.store_rest.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import ru.store.store_rest.dropNamePackage
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.LoggingEvent
import ru.store.store_rest.model.RequestResponse

@Component
class LogAspectGeneral(private val eventPublisher: ApplicationEventPublisher) {

    @Value("\${spring.kafka.topic.name}")
    private lateinit var projectName: String

    fun log(jp: JoinPoint, requestResponse: RequestResponse , body: String) {
        val methodSignature = jp.signature as MethodSignature
        val log = Logging(
            projectName = projectName,
            className = methodSignature.declaringTypeName.dropNamePackage(),
            methodName = methodSignature.name,
            typeMessage = requestResponse,
            requestResponse = body
        )
        eventPublisher.publishEvent(LoggingEvent(this, log))
    }

    fun logError(jp: JoinPoint, exception: Exception) {
        val methodSignature = jp.signature as MethodSignature
        val log = Logging(
            projectName = projectName,
            className = methodSignature.declaringTypeName.dropNamePackage(),
            methodName = methodSignature.name,
            typeMessage = RequestResponse.RESPONSE,
            error = exception.message
        )
        eventPublisher.publishEvent(LoggingEvent(this, log))
    }
}