package ru.store.store_rest.config

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ru.store.store_rest.util.LoggingInterception

@Component
class WebConfig(private val interception: LoggingInterception): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interception)
    }
}