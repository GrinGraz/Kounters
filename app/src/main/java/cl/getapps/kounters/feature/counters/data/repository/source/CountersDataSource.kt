package cl.getapps.kounters.feature.counters.data.repository.source

import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

interface CountersDataSource {
    suspend fun getCounters(): CountersResponse
    suspend fun saveCounter(title: String): CountersResponse
    suspend fun removeCounter(id: Int): CountersResponse
    suspend fun incrementCounter(id: Int): CountersResponse
    suspend fun decrementCounter(id: Int): CountersResponse
}
