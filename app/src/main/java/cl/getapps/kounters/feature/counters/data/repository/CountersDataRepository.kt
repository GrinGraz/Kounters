package cl.getapps.kounters.feature.counters.data.repository

import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.domain.model.Counter
import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository
import cl.getapps.kounters.feature.counters.domain.toCounter

class CountersDataRepository(
    private val remoteDataSource: CountersDataSource
) : CountersRepository {
    override suspend fun getCounters(): Counters {
        return remoteDataSource.getCounters().map { it.toCounter() }
    }

    override suspend fun saveCounter(title: String): Counter {
        return remoteDataSource.saveCounter(title).toCounter()
    }

    override suspend fun removeCounter(id: Int): Unit? {
        return remoteDataSource.removeCounter(id)
    }

    override suspend fun incrementCounter(id: Int): Counter {
        return remoteDataSource.incrementCounter(id).toCounter()
    }

    override suspend fun decrementCounter(id: Int): Counter {
        return remoteDataSource.decrementCounter(id).toCounter()
    }
}
