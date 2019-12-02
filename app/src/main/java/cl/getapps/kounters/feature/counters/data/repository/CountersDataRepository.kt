package cl.getapps.kounters.feature.counters.data.repository

import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository
import cl.getapps.kounters.feature.counters.domain.toCounter

class CountersDataRepository(
    private val remoteDataSource: CountersDataSource
) : CountersRepository {
    override suspend fun getCounters(): Counters {
        return remoteDataSource.getCounters().map { it.toCounter() }
    }

    override suspend fun saveCounter(title: String): Counters {
        return remoteDataSource.saveCounter(title).map { it.toCounter() }
    }

    override suspend fun removeCounter(id: String): Counters {
        return remoteDataSource.removeCounter(id).map { it.toCounter() }
    }

    override suspend fun incrementCounter(id: String): Counters {
        return remoteDataSource.incrementCounter(id).map { it.toCounter() }
    }

    override suspend fun decrementCounter(id: String): Counters {
        return remoteDataSource.decrementCounter(id).map { it.toCounter() }
    }
}
