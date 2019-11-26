package cl.getapps.kounters.feature.counters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.getapps.kounters.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CountersFragment.newInstance())
                    .commitNow()
        }
    }

}
