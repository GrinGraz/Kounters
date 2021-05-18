package cl.getapps.kounters.feature.counters.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.getapps.kounters.base.data.Result
import cl.getapps.kounters.feature.counters.domain.model.Counter
import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.domain.usecase.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownServiceException

class CountersViewModel(
    private val decrementCounter: DecrementCounter,
    private val incrementCounter: IncrementCounter,
    private val getCounters: GetCounters,
    private val removeCounter: RemoveCounter,
    private val saveCounter: SaveCounter
) : ViewModel() {

    val items = MutableLiveData<Result<Counters>>().apply { value = Result.Loading }
    val item = MutableLiveData<Result<Counter>>().apply { value = Result.Loading }
    val itemRemoved = MutableLiveData<Result<Unit?>>().apply { value = Result.Loading }
    val totalCount = MutableLiveData<Int>().apply { value = 0 }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error -> $throwable")
        when (throwable) {
            is HttpException           -> items.value = Result.Error(throwable)
            is InterruptedIOException  -> items.value = Result.Error(throwable)
            is SocketException         -> items.value = Result.Error(throwable)
            is SocketTimeoutException  -> items.value = Result.Error(throwable)
            is UnknownServiceException -> items.value = Result.Error(throwable)
            else                       -> items.value = Result.Error(throwable as Exception)
        }
    }

    fun decrement(id: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            item.value = Result.Success(decrementCounter(id))
        }
    }

    fun increment(id: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            item.value = Result.Success(incrementCounter(id))
        }
    }

    fun fetchCounters() {
        items.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            items.value = Result.Success(getCounters())
        }
    }

    fun remove(id: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            itemRemoved.value = Result.Success(removeCounter(id))
        }
    }

    fun save(title: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            item.value = Result.Success(saveCounter(title))
        }
    }
}
