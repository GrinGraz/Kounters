package cl.getapps.kounters.feature.counters.data.repository.source

import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterEntity
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

interface CountersDataSource {
    suspend fun getCounters(): CountersResponse
    suspend fun saveCounter(title: String): CounterEntity
    suspend fun removeCounter(id: Int): Unit?
    suspend fun incrementCounter(id: Int): CounterEntity
    suspend fun decrementCounter(id: Int): CounterEntity
}
