package cl.getapps.kounters.feature.counters.di

import android.content.Context
import android.net.ConnectivityManager
import cl.getapps.kounters.BuildConfig
import cl.getapps.kounters.feature.counters.data.repository.CountersDataRepository
import cl.getapps.kounters.feature.counters.data.repository.source.CountersDataSource
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

const val BASE_API_URL = "http://192.168.42.244:3000/api/v1/"

val module = module {

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
            })
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
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
