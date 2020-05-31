package com.example.ruletchef.api

import com.example.ruletchef.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("api-token-auth/")
    fun auth(
        @Field("username") email: String,
        @Field("password") password: String
    ) : Call< Token >

    @FormUrlEncoded
    @POST("api/register/")
    fun register(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("password2") password2: String
    ) : Call<User>

    @GET("api/entity/{entityId}/order/")
    fun getOrders(
        @Path("entityId") entityId: Int,
        @Header("Authorization") token: String?,
        @Query("order_item_status") status: State,
        @Query("status") mainStatus: State = State.NEW
    ) : Call< MutableList<Order> >

    @GET("api/entity/{entityId}/menuitem/")
    fun getMenuItems(
        @Path("entityId") entityId: Int,
        @Header("Authorization") token: String?,
        @Query("category") categoryId: Int? = null
    ) : Call< MutableList<MenuItem> >

    @GET("api/entity/{entityId}/category/")
    fun getCategories(
        @Path("entityId") entityId: Int,
        @Header("Authorization") token: String?
    ) : Call< MutableList<Category> >

    @POST("api/entity/{entityId}/order/")
    fun createOrder(
        @Body order: Order,
        @Path("entityId") entityId: Int,
        @Header("Authorization") token: String?
    ) : Call<Order>

    @GET("account/me/")
    fun getMe(
        @Header("Authorization") token: String?
    ) : Call<User>
}