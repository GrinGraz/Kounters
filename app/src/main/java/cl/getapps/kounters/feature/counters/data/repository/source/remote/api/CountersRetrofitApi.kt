package cl.getapps.kounters.feature.counters.data.repository.source.remote.api

import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CounterBodyRequest
import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.CountersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface CountersRetrofitApi {
    @GET("counters")
    suspend fun getCounters(): CountersResponse

    @POST("counter")
    suspend fun saveCounter(@Body body: CounterBodyRequest): CountersResponse

    @POST("counter/inc")
    suspend fun incrementCounter(@Body body: CounterBodyRequest): CountersResponse

    @POST("counter/dec")
    suspend fun decrementCounter(@Body body: CounterBodyRequest): CountersResponse

    @HTTP(method = "DELETE", path = "counter", hasBody = true)
    suspend fun deleteCounter(@Body body: CounterBodyRequest): CountersResponse
}
