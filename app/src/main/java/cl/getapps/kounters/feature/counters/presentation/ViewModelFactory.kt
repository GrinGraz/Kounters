package cl.getapps.kounters.feature.counters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.getapps.kounters.feature.counters.domain.repository.CountersRepository
import cl.getapps.kounters.feature.counters.domain.usecase.*

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val countersRepository: CountersRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CountersViewModel::class.java) ->
                    CountersViewModel(
                        DecrementCounter(countersRepository),
                        IncrementCounter(countersRepository),
                        GetCounters(countersRepository),
                        RemoveCounter(countersRepository),
                        SaveCounter(countersRepository)
                    )
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
