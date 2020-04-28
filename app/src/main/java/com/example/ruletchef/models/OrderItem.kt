package com.example.ruletchef.models

data class OrderItem (
    val image: String,
    val description: String,
    val type: String,
    val amount: Int,
    val wishes: String,
    val state: State
)


enum class State {
    EMPTY,
    PROCCESSING,
    DONE
}