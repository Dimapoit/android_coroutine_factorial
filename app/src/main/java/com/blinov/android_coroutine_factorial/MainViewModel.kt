package com.blinov.android_coroutine_factorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blinov.android_coroutine_factorial.Result
import kotlinx.coroutines.*
import java.math.BigInteger

class MainViewModel : ViewModel() {

    private  val coroutineScope = CoroutineScope(Dispatchers.Main + CoroutineName("My coroutine"))

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }
        coroutineScope.launch {
            val number = value.toLong()
            val result = withContext(Dispatchers.Default) {
                factorial(number)
            }
            _state.value = Result(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    private fun factorial(value: Long): String {

        var result = BigInteger.ONE
        for (i in 1..value) {
            result = result.multiply(BigInteger.valueOf(i))
        }
        return result.toString()
    }
}