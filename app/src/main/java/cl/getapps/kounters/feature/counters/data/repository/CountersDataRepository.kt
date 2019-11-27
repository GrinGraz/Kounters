package cl.getapps.kounters.feature.counters.data.repository

import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository

class CountersDataRepository(
    private val remoteDataSource: CountersDataSource,
    private val localDataSource: CountersDataSource
) : CountersRepository {
    override suspend fun getCounters(): Counters {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveCounter(title: String): Counters {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeCounter(id: Int): Counters {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun incrementCounter(id: Int): Counters {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun decrementCounter(id: Int): Counters {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
