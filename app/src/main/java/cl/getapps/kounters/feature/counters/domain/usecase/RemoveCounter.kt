package cl.getapps.kounters.feature.counters.domain.usecase

import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository

class RemoveCounter(private val countersRepository: CountersRepository) {
    suspend operator fun invoke(id: Int): Unit? {
        return countersRepository.removeCounter(id)
    }
}
