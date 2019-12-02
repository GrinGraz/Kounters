package cl.getapps.kounters.feature.counters.data.repository.source

import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

interface CountersDataSource {
    suspend fun getCounters(): CountersResponse
    suspend fun saveCounter(title: String): CountersResponse
    suspend fun removeCounter(id: String): CountersResponse
    suspend fun incrementCounter(id: String): CountersResponse
    suspend fun decrementCounter(id: String): CountersResponse
}
