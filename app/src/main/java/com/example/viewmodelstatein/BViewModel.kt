package com.example.viewmodelstatein

import androidx.lifecycle.ViewModel

class BViewModel : ViewModel() {

    private val repo = SingletonRepo.get()
    val state = repo.someState

    fun incrementState() {
        repo.incrementState()
    }

}