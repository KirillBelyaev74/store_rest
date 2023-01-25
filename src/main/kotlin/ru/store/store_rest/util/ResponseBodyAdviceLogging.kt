package ru.store.store_rest.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.LoggingEvent
import ru.store.store_rest.model.RequestResponse

@RestControllerAdvice
class ResponseBodyAdviceLogging<R>(private val eventPublisher: ApplicationEventPublisher): ResponseBodyAdvice<R> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(
        body: R?,
        methodParameter: MethodParameter,
        mediaType: MediaType,
        converterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): R? {
        if (!methodParameter.method.name.contains("xception")) {
            val log = Logging(
                projectName = "store_rest",
                url = request.uri.path,
                methodName = request.method.name,
                typeMessage = RequestResponse.RESPONSE,
                requestResponse = if (body != null && body is List<*> ) "size ${body.size}" else body.toString()
            )
            eventPublisher.publishEvent(LoggingEvent(this, log))
        }
        return body
    }
}