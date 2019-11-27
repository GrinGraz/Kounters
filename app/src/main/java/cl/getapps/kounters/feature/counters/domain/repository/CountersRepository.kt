package cl.getapps.kounters.feature.counters.domain.repository

import cl.getapps.kounters.feature.counters.domain.model.Counters

interface CountersRepository {
    suspend fun getCounters(): Counters
    suspend fun saveCounter(title: String): Counters
    suspend fun removeCounter(id: Int): Counters
    suspend fun incrementCounter(id: Int): Counters
    suspend fun decrementCounter(id: Int): Counters
}
