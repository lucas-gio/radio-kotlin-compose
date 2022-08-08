package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Configuration
import org.dizitart.no2.IndexOptions
import org.dizitart.no2.IndexType
import org.dizitart.no2.Nitrite
import org.dizitart.no2.objects.ObjectRepository
import org.dizitart.no2.objects.filters.ObjectFilters
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ConfigurationRepositoryImpl(
    private val database: Nitrite,
) : ConfigurationRepository {
    private var logger: Logger = LoggerFactory.getLogger(ConfigurationRepositoryImpl::class.java)

    private fun getRepository(): ObjectRepository<Configuration> {
        return database.getRepository(Configuration::class.java)
    }

    override fun upsert(configuration: Configuration) {
        getRepository().update(configuration, true)
        logger.atDebug().log("Actualizada la configuración ${configuration.key}, valor: ${configuration.value}")
    }

    override fun find(key: String): Configuration? {
        logger.atDebug().log("Buscando configuración $key")
        val config = getRepository()
            .find(
                ObjectFilters.eq("key", key)
            )
            ?.firstOrDefault()
        logger.atDebug().log("Leída la configuración $key, valor: ${config?.value?: "n.d"}")
        return config
    }

    override fun createIndexes(){
        val repository = getRepository()
        if (!repository.hasIndex("key")) {
            repository.createIndex("key",
                IndexOptions.indexOptions(IndexType.Unique, false)
            )
        }
    }
}