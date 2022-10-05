package ru.store.store_rest.util

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.RequestResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingInterception: HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.method == HttpMethod.GET.name && handler is HandlerMethod && !request.requestURI.contains("/error")) {
            val handlerMethod = handler as HandlerMethod
            val log = Logging(
                "store_rest",
                handlerMethod.beanType.simpleName,
                handlerMethod.method.name,
                RequestResponse.REQUEST,
                "[${request.requestURI} : ${request.method}]")
            println(log)
        }
        return true
    }
}