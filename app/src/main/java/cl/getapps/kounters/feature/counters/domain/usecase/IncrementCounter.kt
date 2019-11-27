package cl.getapps.kounters.feature.counters.domain.usecase

import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository

class IncrementCounter(private val countersRepository: CountersRepository) {
    suspend operator fun invoke(id: Int): Counters {
        return countersRepository.incrementCounter(id)
    }
}
