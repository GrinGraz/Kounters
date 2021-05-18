package cl.getapps.kounters.feature.counters.data.repository.source.remote.api

import cl.getapps.kounters.feature.counters.data.repository.source.remote.entity.*
import retrofit2.http.*

interface CountersRetrofitApi {
    @GET("counters")
    suspend fun getCounters(): CountersResponse

    @POST("counter")
    suspend fun saveCounter(@Body body: CounterCreateBodyRequest): CounterEntity

    @POST("counter")
    suspend fun saveCounters(@Body body: CountersCreateBodyRequest): CountersResponse

    @PATCH("counter/inc")
    suspend fun incrementCounter(@Body body: CounterBodyRequest): CounterEntity

    @PATCH("counter/dec")
    suspend fun decrementCounter(@Body body: CounterBodyRequest): CounterEntity

    @HTTP(method = "DELETE", path = "counter", hasBody = true)
    suspend fun deleteCounter(@Body body: CounterBodyRequest): Unit?
}
