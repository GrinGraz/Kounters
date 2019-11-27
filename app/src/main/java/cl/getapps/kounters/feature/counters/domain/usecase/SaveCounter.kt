package cl.getapps.kounters.feature.counters.domain.usecase

import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository

class SaveCounter(private val countersRepository: CountersRepository) {
    suspend operator fun invoke(title: String): Counters {
        return countersRepository.saveCounter(title)
    }
}
