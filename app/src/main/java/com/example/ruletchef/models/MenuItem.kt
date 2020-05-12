package com.example.ruletchef.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MenuItem (

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("image")
    val image: String,

    @Expose
    @SerializedName("item_description")
    val description: String,

    @Expose
    @SerializedName("item_type")
    val type: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("available")
    val available: Boolean,

    @SerializedName("category")
    val categoryId: Int,

    @SerializedName("entity")
    val entityId: Int
)