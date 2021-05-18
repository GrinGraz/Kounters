package cl.getapps.kounters.feature.counters.data.repository.source.remote.entity

data class CounterBodyRequest(val id: Int = -1)
data class CounterCreateBodyRequest(val name: String = "")
data class CountersCreateBodyRequest(val names: List<String> = listOf())
