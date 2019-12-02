package cl.getapps.kounters.feature.counters.data.repository.source.remote

import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.data.repository.source.remote.api.CountersRetrofitApi
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterBodyRequest
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

class CountersApiDataSource(private val countersRetrofitApi: CountersRetrofitApi) :
    CountersDataSource {
    override suspend fun getCounters(): CountersResponse {
        return countersRetrofitApi.getCounters()
    }

    override suspend fun saveCounter(title: String): CountersResponse {
        return countersRetrofitApi.saveCounter(CounterBodyRequest(title = title))
    }

    override suspend fun removeCounter(id: String): CountersResponse {
        return countersRetrofitApi.deleteCounter(CounterBodyRequest(id = id))
    }

    override suspend fun incrementCounter(id: String): CountersResponse {
        return countersRetrofitApi.incrementCounter(CounterBodyRequest(id = id))
    }

    override suspend fun decrementCounter(id: String): CountersResponse {
        return countersRetrofitApi.decrementCounter(CounterBodyRequest(id = id))
    }
}
