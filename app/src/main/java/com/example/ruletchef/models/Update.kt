package com.example.ruletchef.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Update (
    @Expose
    @SerializedName("order_item_id")
    val orderItemId: Int,

    @Expose
    @SerializedName("order_id")
    val orderId: Int,

    @Expose
    @SerializedName("cook")
    val cook: Int?,

    @Expose
    @SerializedName("status")
    val state: State
)