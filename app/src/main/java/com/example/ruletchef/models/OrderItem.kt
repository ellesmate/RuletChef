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
    val wishes: String,

    @Expose
    @SerializedName("status")
    var state: State,

    @Expose
    @SerializedName("cook")
    var cook: Int?
) {
    lateinit var image: String
    lateinit var description: String
    lateinit var type: String
}


enum class State {
    @Expose
    @SerializedName("NEW")
    NEW,

    @Expose
    @SerializedName("COO")
    COOKING,

    @Expose
    @SerializedName("DEL")
    DELIVERING,

    @Expose
    @SerializedName("PAY")
    PAYING,

    @Expose
    @SerializedName("DON")
    DONE;

    override fun toString(): String {
        return super.toString().take(3)
    }
}