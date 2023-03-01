package ru.store.store_rest.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.LoggingEvent
import ru.store.store_rest.model.RequestResponse
import java.lang.reflect.Type
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class RequestBodyAdviceLogging(
    private val eventPublisher: ApplicationEventPublisher,
    private val request: HttpServletRequest
    ): RequestBodyAdviceAdapter() {

    @Value("\${spring.kafka.topic.name}")
    private lateinit var projectName: String

    override fun supports(methodParameter: MethodParameter, targetType: Type, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun afterBodyRead(
        body: Any,
        inputMessage: HttpInputMessage,
        methodParameter: MethodParameter,
        type: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Any {
        val log = Logging(
            projectName = projectName,
            url = request.requestURI,
            methodName = methodParameter.method.name,
            typeMessage = RequestResponse.REQUEST,
            requestResponse = body.toString()
        )
        eventPublisher.publishEvent(LoggingEvent(this, log))
        return body
    }
}