package cl.getapps.kounters.feature.counters.domain.model

typealias Counters = List<Counter>

data class Counter(val id: Int, val title: String, val count: Int)
