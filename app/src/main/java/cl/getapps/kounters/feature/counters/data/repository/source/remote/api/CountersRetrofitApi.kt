package cl.getapps.kounters.feature.counters.data.repository.source.remote.api

import cl.getapps.kounters.base.data.Result
import cl.getapps.kounters.feature.counters.domain.model.Counters
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface CountersRetrofitApi {
    @GET("/counters")
    suspend fun getCounters(): Result<Counters>

    @POST("/counter")
    suspend fun saveCounter(@Body title: String): Result<Counters>

    @POST("/counter/inc")
    suspend fun incrementCounter(@Body id: String): Result<Counters>

    @POST("/counter/dec")
    suspend fun decrementCounter(@Body id: String): Result<Counters>

    @DELETE("/counter")
    suspend fun deleteCounter(@Body id: String): Result<Counters>
}
