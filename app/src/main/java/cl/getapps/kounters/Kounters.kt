package cl.getapps.kounters

import android.app.Application
import cl.getapps.kounters.feature.counters.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Kounters : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@Kounters)
            modules(module)
        }

    }
}
