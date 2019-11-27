package cl.getapps.kounters.feature.counters.data.repository.source.remote

import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.data.repository.source.remote.api.CountersRetrofitApi
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

class CountersApiDataSource(private val countersRetrofitApi: CountersRetrofitApi) : CountersDataSource {
    override suspend fun getCounters(): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveCounter(title: String): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeCounter(id: Int): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun incrementCounter(id: Int): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun decrementCounter(id: Int): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
