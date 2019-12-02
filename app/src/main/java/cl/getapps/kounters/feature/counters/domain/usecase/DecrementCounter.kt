package cl.getapps.kounters.feature.counters.domain.usecase

import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository

class DecrementCounter(private val countersRepository: CountersRepository) {
    suspend operator fun invoke(id: String): Counters {
        return countersRepository.decrementCounter(id)
    }
}
