package com.blinov.android_coroutine_factorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blinov.android_coroutine_factorial.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }
        viewModelScope.launch {
            val number = value.toLong()
            val result = factorial(number)
            _state.value = Result(result)
        }
    }

    private suspend fun factorial(value: Long): String {
        return withContext(
            Dispatchers.Default
        ) {
            var result = BigInteger.ONE
            for (i in 1..value) {
                result = result.multiply(BigInteger.valueOf(i))
            }
            result.toString()
        }
    }
}