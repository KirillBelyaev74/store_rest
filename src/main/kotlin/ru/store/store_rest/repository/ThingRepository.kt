package ru.store.store_rest.repository

import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import ru.store.store_rest.*
import ru.store.store_rest.config.EndpointsConfig
import ru.store.store_rest.model.BrandCategorySizeDto
import ru.store.store_rest.model.ThingDto

@Repository
open class ThingRepository(private val webClient: WebClient, private val endpoints: EndpointsConfig) :
    IThingRepository {

    override fun save(thing: ThingDto): Boolean {
        val uri = Uri.uriThing(endpoints = endpoints)
        val response = webClient.postMono(uri, Int::class.java, thing)
        return when (response) {
            1 -> true; else -> false
        }
    }

    override fun findAllThings(): MutableList<ThingDto>? {
        val uri = Uri.uriThing(endpoints = endpoints)
        return webClient.getFlux(uri, ThingDto::class.java)
    }

    override fun findAllCategory(): MutableList<BrandCategorySizeDto>? {
        val uri = Uri.uriThing(endpoints.findAllCategory, endpoints)
        return webClient.getFlux(uri, BrandCategorySizeDto::class.java)
    }

    override fun findAllBrand(): MutableList<BrandCategorySizeDto>? {
        val uri = Uri.uriThing(endpoints.findAllBrand, endpoints)
        return webClient.getFlux(uri, BrandCategorySizeDto::class.java)
    }

    override fun findAllSize(): MutableList<BrandCategorySizeDto>? {
        val uri = Uri.uriThing(endpoints.findAllSize, endpoints)
        return webClient.getFlux(uri, BrandCategorySizeDto::class.java)
    }

    override fun findAllThingsByCategory(category: String): MutableList<ThingDto>? {
        val uri = Uri.uriThing("${endpoints.findAllCategory}/$category", endpoints)
        return webClient.getFlux(uri, ThingDto::class.java)
    }

    override fun findAllThingsByBrand(brand: String): MutableList<ThingDto>? {
        val uri = Uri.uriThing("${endpoints.findAllBrand}/$brand", endpoints)
        return webClient.getFlux(uri, ThingDto::class.java)
    }

    override fun findAllThingsBySize(size: String): MutableList<ThingDto>? {
        val uri = Uri.uriThing("${endpoints.findAllSize}/$size", endpoints)
        return webClient.getFlux(uri, ThingDto::class.java)
    }

    override fun findAllThingsByMiddlePrice(low: Low, high: High): MutableList<ThingDto>? {
        val uri = Uri.uriThing("$low/$high", endpoints)
        return webClient.getFlux(uri, ThingDto::class.java)
    }

    override fun deleteById(id: Long): Boolean {
        val uri = Uri.uriThing("$id", endpoints)
        val response = webClient.deleteMono(uri, Int::class.java)
        return when (response) {
            1 -> true; else -> false
        }
    }
}