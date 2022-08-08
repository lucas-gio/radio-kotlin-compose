package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Country
import org.dizitart.no2.IndexOptions
import org.dizitart.no2.IndexType
import org.dizitart.no2.Nitrite
import org.dizitart.no2.exceptions.UniqueConstraintException
import org.dizitart.no2.objects.ObjectRepository
import org.dizitart.no2.objects.filters.ObjectFilters
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CountryRepositoryImpl(
    private val database: Nitrite,
    private var logger: Logger = LoggerFactory.getLogger(CountryRepositoryImpl::class.java)
) : CountryRepository {

    private fun getRepository(): ObjectRepository<Country> {
        return database.getRepository(Country::class.java)
    }

    override fun removeAll() {
        getRepository().remove(ObjectFilters.ALL)
        logger.atDebug().log("Removed all countries from database.")
    }

    override fun saveAll(countries: List<Country>) {
        val repository = getRepository()
        countries.forEach {
            try {
                repository.insert(it)
                logger.atDebug().log("Inserted country: ${it.code}")
            }
            catch (e: UniqueConstraintException) {
                logger.atDebug().log("REPEATED IGNORED COUNTRY: ${it.code}")
            }
        }
    }

    override fun createIndexes() {
        val repository = getRepository()
        if (!repository.hasIndex("code")) {
            repository.createIndex(
                "code",
                IndexOptions.indexOptions(IndexType.NonUnique, false)
            )
        }
    }


    override fun setFavourite(countryName: String, radioName: String) {
        TODO("")//
        /*database
            .getRepository(Country::class.java)
            .update(

            )*/
    }
}