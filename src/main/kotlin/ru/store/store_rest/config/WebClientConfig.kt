package ru.store.store_rest.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
open class WebClientConfig {

    private val fiveSecond: Long = 5

    @Value("\${spring.kafka.topic.name}")
    private lateinit var projectName: String

    @Bean
    open fun webClient(httpClient: HttpClient): WebClient {
        return WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("project_name", projectName)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    @Bean
    open fun httpClient(): HttpClient {
        return HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofSeconds(fiveSecond))
            .doOnConnected {
                it.addHandlerLast(ReadTimeoutHandler(fiveSecond, TimeUnit.SECONDS))
                it.addHandlerLast(WriteTimeoutHandler(fiveSecond, TimeUnit.SECONDS))
            }
    }
}