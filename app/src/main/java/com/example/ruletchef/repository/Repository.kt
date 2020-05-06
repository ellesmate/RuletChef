package com.example.ruletchef.repository

import android.util.Log
import androidx.lifecycle.*
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.livedata.OrderLiveData
import com.example.ruletchef.models.*
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


object Repository {
    private const val TAG: String = "RequestRepository"

    var token: MutableLiveData<Token?> = MutableLiveData()
    val me: Int = 1

    fun auth(email: String, password: String) : LiveData<Boolean> {
        return object: LiveData<Boolean>() {
            override fun onActive() {
                super.onActive()

                RetrofitBuilder.apiService.auth(email, password).enqueue(object: Callback<Token> {
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        if(response.isSuccessful) {
                            val newToken = response.body()

                            when(newToken == null) {
                                true -> {
                                    token.postValue(null)
                                    value = false
                                }
                                false -> {
                                    token.postValue(newToken)
                                    value = true
                                }
                            }
                        } else {
                            handleResponseCode(response.code())
                            value = false
                        }
                    }

                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        Log.d(TAG, "Auth onFailure method", t)
                    }
                })
            }
        }
    }


    fun handleResponseCode(code: Int) {
        when(code) {
            401 -> {
                Log.d(TAG, "Unauthorized")
                token.postValue(null)
            }
            403 -> {
                Log.d(TAG, "Forbidden")
            }
        }
    }

    fun fetchOrders(menuItemMap: Map<Int, MenuItem>) : LiveData<MutableList<Order>> {
//        return OrderLiveData(menuItemMap)
        return object : MutableLiveData<MutableList<Order>>() {
            var isTouchable: Boolean = true

            fun fetch() {
                Log.d("NavigationFragment", "Start Load")
                Log.d("NavigationFragment", this.hashCode().toString())
                RetrofitBuilder.apiService.getOrders(1, Repository.token.value?.toString())
                    .enqueue(object: Callback<MutableList<Order>> {
                        override fun onResponse(
                            call: Call<MutableList<Order>>,
                            response: Response<MutableList<Order>>
                        ) {
                            if (response.isSuccessful) {
                                val orders = response.body()

                                orders?.forEach {order ->
                                    println(order)

                                    order.items.forEach { item ->
                                        val menuItem = menuItemMap[item.menuItemId]
                                        item.apply {
                                            image = menuItem!!.image
                                            description = menuItem.description
                                            type = menuItem.type
                                        }
                                    }
                                }
                                isTouchable = false
                                value = orders
                            } else {
                                handleResponseCode(response.code())
                            }
                        }
                        override fun onFailure(call: Call<MutableList<Order>>, t: Throwable) {
                            Log.d(TAG, "Fetch order error", t)
                        }
                    })
            }


//            override fun pushBack(order: Order) {
//                order.items.forEach { item ->
//                    val menuItem = menuItemMap[item.menuItemId]
//                    item.apply {
//                        image = menuItem!!.image
//                        description = menuItem.description
//                        type = menuItem.type
//                    }
//                }
//
//                if (value == null) {
//                    value = mutableListOf(order)
//                } else {
//                    value?.add(order)
//                    value = value
//                }
//
//            }

            override fun onActive() {
                super.onActive()

                if (value == null) {
                    isTouchable = true
                }

                if (isTouchable) {
                    fetch()
                } else {
                    value = value
                }

            }
        }
    }


    fun fetchMenuItems(): MutableLiveData<Map<Int, MenuItem>> {
        return object: MutableLiveData<Map<Int, MenuItem>>() {
            fun fetch() {
                RetrofitBuilder.apiService.getMenuItems(1, token.value?.toString())
                    .enqueue(object: Callback< List<MenuItem>> {
                        override fun onResponse(
                            call: Call<List<MenuItem>>,
                            response: Response<List<MenuItem>>
                        ) {
                            if (response.isSuccessful) {
                                val map = mutableMapOf<Int, MenuItem>()
                                response.body()?.forEach {
                                    map[it.id] = it
                                }
//                            menu.postValue(map)
                                isTouchable = false
                                value = map
                            } else {
                                handleResponseCode(response.code())
                            }
                        }

                        override fun onFailure(call: Call<List<MenuItem>>, t: Throwable) {
                            Log.e(TAG, "MenuItem retrieve error.", t)
                        }
                    })
            }
            var isTouchable = true

            override fun onActive() {
                super.onActive()
                if (value == null) {
                    isTouchable = true
                }

                if (isTouchable) {
                    fetch()
                }
            }
        }
    }



}