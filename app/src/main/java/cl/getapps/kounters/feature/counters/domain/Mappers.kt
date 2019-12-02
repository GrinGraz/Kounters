package cl.getapps.kounters.feature.counters.domain

import cl.getapps.kounters.feature.counters.domain.model.Counter
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterEntity as RemoteCounterEntity

fun RemoteCounterEntity.toCounter() = Counter(this.id, this.title, this.count.toString())
