package com.example.ruletchef.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderItem (
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("amount")
    val amount: Int,

    @Expose
    @SerializedName("menu_item")
    val menuItemId: Int,

    @Expose
    @SerializedName("order")
    val orderId: Int,

    @Expose
    @SerializedName("wishes")
    val wishes: String
) {
    lateinit var image: String
    lateinit var description: String
    lateinit var type: String
    val state: State  = State.EMPTY
}


enum class State {
    EMPTY,
    PROCCESSING,
    DONE
}