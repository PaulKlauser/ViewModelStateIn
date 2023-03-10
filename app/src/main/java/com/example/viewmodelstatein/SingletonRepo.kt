package com.example.viewmodelstatein

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SingletonRepo private constructor() {
    companion object {
        var INSTANCE: SingletonRepo? = null
        fun get(): SingletonRepo {
            return INSTANCE ?: SingletonRepo().apply {
                INSTANCE = this
            }
        }
    }

    private val _someState = MutableStateFlow(1)
    val someState = _someState.asStateFlow()

    fun incrementState() {
        _someState.update { it + 1 }
    }
}