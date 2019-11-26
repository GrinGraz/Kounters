package cl.getapps.kounters.feature.counters.data.repository.source.remote

import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.data.repository.source.remote.api.CountersRetrofitApi
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse

class CountersApiDataSource(val countersRetrofitApi: CountersRetrofitApi) : CountersDataSource {
    override fun getCounters(): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveCounter(title: String): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeCounter(id: Int): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun incrementCounter(id: Int): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun decrementCounter(id: Int): CountersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
