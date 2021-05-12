package cl.getapps.kounters.feature.counters.data.repository.source.remote

import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.data.repository.source.remote.api.CountersRetrofitApi
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterBodyRequest
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterCreateBodyRequest
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterEntity
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

class CountersApiDataSource(private val countersRetrofitApi: CountersRetrofitApi) :
    CountersDataSource {
    override suspend fun getCounters(): CountersResponse {
        return countersRetrofitApi.getCounters()
    }

    override suspend fun saveCounter(title: String): CounterEntity {
        return countersRetrofitApi.saveCounter(CounterCreateBodyRequest(name = title))
    }

    override suspend fun removeCounter(id: Int): CountersResponse {
        return countersRetrofitApi.deleteCounter(CounterBodyRequest(id = id))
    }

    override suspend fun incrementCounter(id: Int): CountersResponse {
        return countersRetrofitApi.incrementCounter(CounterBodyRequest(id = id))
    }

    override suspend fun decrementCounter(id: Int): CountersResponse {
        return countersRetrofitApi.decrementCounter(CounterBodyRequest(id = id))
    }
}
