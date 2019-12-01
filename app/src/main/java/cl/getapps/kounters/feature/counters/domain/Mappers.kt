package cl.getapps.kounters.feature.counters.domain

import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterEntity as RemoteCounterEntity
import cl.getapps.kounters.feature.counters.data.repository.source.local.entity.CounterEntity as LocalCounterEntity
import cl.getapps.kounters.feature.counters.domain.model.Counter

fun RemoteCounterEntity.toCounter() = Counter(this.id, this.title, this.count.toString())

fun LocalCounterEntity.toCounter() = Counter(this.stub, this.stub, this.stub)
