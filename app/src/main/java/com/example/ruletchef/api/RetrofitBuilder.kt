package com.example.ruletchef.api

import com.example.ruletchef.models.Token
import com.example.ruletchef.repository.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    const val BASE_URL = "http://192.168.0.93:8000/"

//    private var token: Token? = null
//    private val user: User? = null


    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    const val BASE_WS_URL = "ws://192.168.0.93:8000/"

    private val client: OkHttpClient by lazy {
        OkHttpClient()
    }

    fun newWebSocket(url: String, listener: WebSocketListener) : WebSocket {
        val request = Request.Builder()
            .url(BASE_WS_URL+url)
            .addHeader("Authorization", Repository.token.value.toString())
            .build()
        return client.newWebSocket(request, listener)
    }

    val gson: Gson by lazy {
        GsonBuilder().create()
    }



}