package cl.getapps.kounters.feature.counters.di

import android.content.Context
import android.net.ConnectivityManager
import cl.getapps.kounters.feature.counters.data.repository.CountersDataRepository
import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
import cl.getapps.kounters.feature.counters.data.repository.source.local.database.CountersDataBase
import cl.getapps.kounters.feature.counters.data.repository.source.remote.CountersApiDataSource
import cl.getapps.kounters.feature.counters.data.repository.source.remote.api.CountersRetrofitApi
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository
import cl.getapps.kounters.feature.counters.presentation.ViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_VERSION = "v1"
const val BASE_API_URL = "http://0.0.0.0:3000/api/$API_VERSION"

val module = module {

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>()
            .create(CountersRetrofitApi::class.java) as CountersRetrofitApi
    }

    factory<CountersDataSource> { CountersApiDataSource(get()) }

    single<CountersRepository> { CountersDataRepository(get()) }

    single { ViewModelFactory(get()) }
}
