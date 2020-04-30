package com.example.ruletchef.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


data class Order (
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("entity")
    val entity: Int,

    @Expose
    @SerializedName("time")
    val time: Date,

    @Expose
    @SerializedName("take_out")
    val takeOut: Boolean,

//    @Expose
//    @SerializedName("orderitem_set")
//    val metaItems: List<MetaItem>,

    @Expose
    @SerializedName("orderitem_set")
    val items: List<OrderItem>
)


