package cl.getapps.kounters.feature.counters.presentation

import androidx.lifecycle.ViewModel
import cl.getapps.kounters.feature.counters.domain.usecase.*

class CountersViewModel(private val decrementCounter: DecrementCounter,
                        private val incrementCounter: IncrementCounter,
                        private val getCounters: GetCounters,
                        private val removeCounter: RemoveCounter,
                        private val saveCounter: SaveCounter) : ViewModel() {

    // TODO: Implement the ViewModel
}
