package ru.store.store_rest.util

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.RequestResponse

@RestControllerAdvice
class ResponseBodyAdviceLogging<R>: ResponseBodyAdvice<R> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(body: R?, methodParameter: MethodParameter, mediaType: MediaType, converterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): R? {
        val log = Logging(
            "store_rest",
            converterType.simpleName,
            methodParameter.method.name,
            RequestResponse.RESPONSE,
            body.toString()
        )
        println(log)
        return body
    }
}