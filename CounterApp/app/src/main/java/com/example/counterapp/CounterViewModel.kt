package com.example.counterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel(private val repository: CounterRepository) : ViewModel() {
    private val _count = mutableStateOf(repository.getCounter().count)
    val count : MutableState<Int> = _count

    fun increment() {
      repository.incrementCounter()
    }

    fun decrement() {
 repository.decrementCounter()
    }

}