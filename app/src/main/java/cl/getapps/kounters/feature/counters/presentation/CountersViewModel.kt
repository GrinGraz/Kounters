package cl.getapps.kounters.feature.counters.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.usecase.*
import cl.getapps.kounters.feature.counters.presentation.CountersSortType.ID
import kotlinx.coroutines.launch

class CountersViewModel(
    private val decrementCounter: DecrementCounter,
    private val incrementCounter: IncrementCounter,
    private val getCounters: GetCounters,
    private val removeCounter: RemoveCounter,
    private val saveCounter: SaveCounter
) : ViewModel() {

    private val items = MutableLiveData<Counters>().apply { value = emptyList() }

    fun decrement(id: Int) {
        viewModelScope.launch {
            items.value = decrementCounter(id)
        }
    }

    fun increment(id: Int) {
        viewModelScope.launch {
            items.value = incrementCounter(id)
        }
    }

    fun counters(sortedBy: CountersSortType = ID) {
        viewModelScope.launch {
            items.value = getCounters()
        }
    }

    fun remove(id: Int) {
        viewModelScope.launch {
            items.value = removeCounter(id)
        }
    }

    fun save(title: String) {
        viewModelScope.launch {
            items.value = saveCounter(title)
        }
    }
}
