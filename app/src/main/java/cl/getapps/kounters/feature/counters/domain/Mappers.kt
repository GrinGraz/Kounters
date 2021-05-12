package cl.getapps.kounters.feature.counters.domain

import cl.getapps.kounters.feature.counters.domain.model.Counter
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterEntity as RemoteCounterEntity

fun RemoteCounterEntity.toCounter() = Counter(id = this.id, name = this.name, count = this.count)
