package com.example.ruletchef.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("username")
    val username: String
)