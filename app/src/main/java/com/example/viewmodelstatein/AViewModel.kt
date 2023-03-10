package com.example.viewmodelstatein

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AViewModel : ViewModel() {

    private val repo = SingletonRepo.get()
    val state = repo.someState.map {
        Log.d("AViewModelMap", "$it")
        it
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    init {
        viewModelScope.launch {
            repo.someState.collect {
                Log.d("AViewModel", "$it")
            }
        }
    }

    fun incrementState() {
        repo.incrementState()
    }

}