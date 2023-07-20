package ru.store.store_rest.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "url")
data class EndpointsConfig(

    var schema: String,
    var host: String,

    var portThing: String,
    var portUser: String,

    var findAllThingsAndSave: String,
    var findAllCategory: String,
    var findAllBrand: String,
    var findAllSize: String,

    var saveUser: String
)