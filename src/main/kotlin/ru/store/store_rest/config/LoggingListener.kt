package ru.store.store_rest.config

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import ru.store.store_rest.model.LoggingEvent

@Component
class LoggingListener {

    @Async
    @EventListener
    fun onApplicationEvent(event: LoggingEvent) {
        println(event.logging)
    }
}