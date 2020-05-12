package com.example.ruletchef.models

import com.google.gson.annotations.SerializedName


data class Category(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("entity")
    val entityId: Int
)