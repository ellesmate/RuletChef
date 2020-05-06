package com.example.ruletchef.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


data class Order (
//    @Expose(serialize = true, deserialize = true)
    @SerializedName("id")
    val id: Int,

//    @Expose
//    @Expose(serialize = true, deserialize = true)
    @SerializedName("entity")
    val entity: Int,

//    @Expose
//    @Expose(serialize = true, deserialize = true)
    @SerializedName("time")
    val time: Date,

//    @Expose
//    @Expose(serialize = true, deserialize = true)
    @SerializedName("take_out")
    val takeOut: Boolean,

//    @Expose
//    @SerializedName("orderitem_set")
//    val metaItems: List<MetaItem>,

//    @Expose
//    @Expose(serialize = true, deserialize = true)
    @SerializedName("orderitem_set")
    val items: List<OrderItem>
)


