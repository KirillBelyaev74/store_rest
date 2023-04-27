package ru.store.store_rest.config

import com.google.common.cache.CacheBuilder
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
open class CacheConfig {

    @Bean
    open fun cacheManager(): CacheManager {
        return object : ConcurrentMapCacheManager() {
            override fun createConcurrentMapCache(name: String): Cache {
                return ConcurrentMapCache(
                    name,
                    CacheBuilder.newBuilder()
                        .initialCapacity(50)
                        .concurrencyLevel(1)
                        .maximumSize(100)
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .build<Any, Any>().asMap(), false
                )
            }
        }
    }
}