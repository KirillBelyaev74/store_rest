package ru.store.store_rest.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import ru.store.store_rest.model.Logging
import ru.store.store_rest.model.LoggingEvent
import ru.store.store_rest.model.RequestResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingInterception(private val eventPublisher: ApplicationEventPublisher): HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.method == HttpMethod.GET.name && handler is HandlerMethod && !request.requestURI.contains("/error")) {
            val log = Logging(
                projectName = "store_rest",
                url = request.requestURI,
                methodName = request.method,
                typeMessage = RequestResponse.REQUEST
            )
            eventPublisher.publishEvent(LoggingEvent(this, log))
        }
        return true
    }
}