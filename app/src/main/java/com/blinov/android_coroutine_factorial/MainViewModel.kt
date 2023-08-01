package com.blinov.android_coroutine_factorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

//    private val _error = MutableLiveData<Boolean>()
//    val error: LiveData<Boolean>
//        get() = _error
//
//    private val _progress = MutableLiveData<Boolean>()
//    val progress: LiveData<Boolean>
//        get() = _progress
//
//    private val _factorial = MutableLiveData<String>()
//    val factorial: LiveData<String>
//        get() = _factorial

    private  val _state = MutableLiveData<State>()
    val state: LiveData<State>
    get() = _state

    fun calculate(value: String?) {
        _state.value = State(isProgress = true)
        //_progress.value = true
        Log.d("MainViewModel", "text $value")
        if(value.isNullOrBlank()) {
//            _progress.value = false
//            _error.value = true
            _state.value = State(isProgress = false, isError = true)
            return
        }
        viewModelScope.launch {
            val number = value.toLong()
            delay(1000)
//            _progress.value = false
//            _factorial.value = number.toString()
            _state.value = State(isProgress = false, factorial = number.toString())
        }
    }
}