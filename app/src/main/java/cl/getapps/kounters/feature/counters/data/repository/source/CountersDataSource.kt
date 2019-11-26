package cl.getapps.kounters.feature.counters.data.repository.source

import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

interface CountersDataSource {
    fun getCounters(): CountersResponse
    fun saveCounter(title: String): CountersResponse
    fun removeCounter(id: Int): CountersResponse
    fun incrementCounter(id: Int): CountersResponse
    fun decrementCounter(id: Int): CountersResponse
}
