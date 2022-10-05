package ru.store.store_rest.config

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import ru.store.store_rest.model.LoggingEvent

@Component
class LoggingListener {

    @EventListener
    fun onApplicationEvent(event: LoggingEvent) {
        println(event.logging)
    }
}