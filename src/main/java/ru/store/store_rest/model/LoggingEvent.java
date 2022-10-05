package ru.store.store_rest.model;

import org.springframework.context.ApplicationEvent;

public class LoggingEvent extends ApplicationEvent {

    private final Logging logging;

    public LoggingEvent(Object source, Logging logging) {
        super(source);
        this.logging = logging;
    }

    public Logging getLogging() {
        return logging;
    }
}
