package com.example.ruletchef.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token (
    @Expose
    @SerializedName("token")
    val token: String
) {
    override fun toString(): String {
        return "Token $token"
    }
}
