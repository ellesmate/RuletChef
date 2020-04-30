package com.example.ruletchef.api

import com.example.ruletchef.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("account/api-token-auth/")
    fun auth(
        @Field("username") email: String,
        @Field("password") password: String
    ) : Call< Token >

    @GET("api/entity/{entityId}/order/")
    fun getOrders(
        @Path("entityId") entityId: Int,
        @Header("Authorization") token: String?
    ) : Call< List<Order> >

//    @GET()
//    fun getMenuItem(
//        @Field("menu_items") menuItemIdList: Set<Int>
//    ) : Call< Map<Int, MenuItem> >
    @GET("api/entity/{entityId}/menuitem/")
    fun getMenuItems(
        @Path("entityId") entityId: Int,
        @Header("Authorization") token: String?
    ) : Call< List<MenuItem> >
}