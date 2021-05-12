package cl.getapps.kounters.feature.counters.data.repository.source.remote.entity

typealias CountersResponse = List<CounterEntity>

data class CounterEntity(val id: Int, val name: String, val count: Int)
