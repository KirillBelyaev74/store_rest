package ru.store.store_rest.util

import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.RequestResponse
import java.lang.reflect.Type

@RestControllerAdvice
class RequestBodyAdviceLogging: RequestBodyAdviceAdapter() {

    override fun supports(methodParameter: MethodParameter, targetType: Type, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun afterBodyRead(body: Any, inputMessage: HttpInputMessage, methodParameter: MethodParameter, type: Type, converterType: Class<out HttpMessageConverter<*>>): Any {
        val log = Logging(
            "store_rest",
            converterType.simpleName,
            methodParameter.method.name,
            RequestResponse.REQUEST,
            body.toString()
        )
        println(log)
        return body
    }
}