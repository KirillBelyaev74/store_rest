package ru.store.store_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableAsync
import ru.store.store_rest.config.EndpointsConfig

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableConfigurationProperties(EndpointsConfig::class)
open class StoreRestApplication

fun main(args: Array<String>) {
    runApplication<StoreRestApplication>(*args)
}