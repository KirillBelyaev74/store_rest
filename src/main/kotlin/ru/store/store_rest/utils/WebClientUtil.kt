package ru.store.store_rest.utils

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import ru.store.store_rest.config.EndpointsConfig
import java.net.URI

fun <Rs> Flux<Rs>.toResponse(): MutableList<Rs>? = collectList().block()

fun <Rs> WebClient.getFlux(uri: URI, classResponse: Class<Rs>): MutableList<Rs>? =
    get().uri(uri).retrieve().bodyToFlux(classResponse).toResponse()

fun <Rs> WebClient.getMono(uri: URI, classResponse: Class<Rs>): Rs? =
    get().uri(uri).retrieve().bodyToMono(classResponse).block()

fun <Rs, Rq> WebClient.postFlux(uri: URI, classResponse: Class<Rs>, request: Rq): MutableList<Rs>? =
    post().uri(uri).body(request!!, request!!::class.java).retrieve().bodyToFlux(classResponse).toResponse()

fun <Rs, Rq> WebClient.postMono(uri: URI, classResponse: Class<Rs>, request: Rq): Rs? =
    post().uri(uri).body(request!!, request!!::class.java).retrieve().bodyToMono(classResponse).block()

fun <Rs> WebClient.deleteMono(uri: URI, classResponse: Class<Rs>): Rs? =
    delete().uri(uri).retrieve().bodyToMono(classResponse).block()

fun UriBuilder.buildThing(path: String? = null, endpoints: EndpointsConfig): URI =
    scheme(endpoints.schema)
        .host(endpoints.host)
        .port(endpoints.portThing)
        .path(endpoints.findAllThingsAndSave)
        .path(path)
        .build()

fun UriBuilder.buildUser(path: String? = null, endpoints: EndpointsConfig): URI =
    scheme(endpoints.schema)
        .host(endpoints.host)
        .port(endpoints.portUser)
        .path(endpoints.portUser)
        .path(path)
        .build()

object Uri {

    fun uriThing(path: String? = null, endpoints: EndpointsConfig): URI {
        return UriComponentsBuilder.newInstance().apply {
            scheme(endpoints.schema)
            host(endpoints.host)
            port(endpoints.portThing)
            path(endpoints.findAllThingsAndSave)
            path(path)
        }.build().toUri()
    }

    fun uriUser(path: String? = null, endpoints: EndpointsConfig): URI {
        return UriComponentsBuilder.newInstance().apply {
            scheme(endpoints.schema)
            host(endpoints.host)
            port(endpoints.portUser)
            path(endpoints.saveUser)
            path(path)
        }.build().toUri()
    }
}
