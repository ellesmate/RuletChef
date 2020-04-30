package com.example.ruletchef.repository

import android.util.Log
import androidx.lifecycle.*
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


object Repository {
    private const val TAG: String = "RequestRepository"

    var token: MutableLiveData<Token?> = MutableLiveData()

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
                        Log.d(TAG, "Auth onFailure method")
                    }
                })
            }
        }
    }

//    var isAuthorized: MutableLiveData<Boolean> = MutableLiveData()

    fun handleResponseCode(code: Int) {
        when(code) {
            401 -> {
                Log.d(TAG, "Unauthorized")
                token.postValue(null)
//                isAuthorized.postValue(false)
            }
            403 -> {
                Log.d(TAG, "Forbidden")
            }
        }
    }

    fun fetchOrders() : LiveData<List<Order>> {

        return object: LiveData<List<Order>>() {

            override fun onActive() {
                super.onActive()
                Log.d("NavigationFragment", "Start Load")



                CoroutineScope(IO).launch {
                    try {
                        val response = RetrofitBuilder.apiService.getOrders(1, token.value?.toString()).execute()

                        if (response.isSuccessful) {

                            val orders = response.body()


//                            val observer = Observer<Map<Int, MenuItem>> {menuItemMap ->
//                                orders?.forEach {order ->
//
//                                    order.items.forEach { item ->
//                                        val menuItem = menuItemMap[item.menuItemId]
//
//                                        item.apply {
//                                            image = menuItem!!.image
//                                            description = menuItem.description
//                                            type = menuItem.type
//                                            wishes = menuItem.wishes
//                                            state = menuItem.state
//                                        }
//                                    }
//
//                                }
//
//
////                                menu.removeObserver()
//                                value = orders
//                            }

                            val menuItemMap = menu.value ?: throw Exception("menuItemMap is empty")

                            orders?.forEach {order ->

                                order.items.forEach { item ->
                                    val menuItem = menuItemMap[item.menuItemId]

                                    item.apply {
                                        image = menuItem!!.image
                                        description = menuItem.description
                                        type = menuItem.type
                                    }
                                }

                            }


                            withContext(Main){
                                value = orders
                            }

                        } else {
                            handleResponseCode(response.code())
                        }
                    } catch(e: Exception) {
                        Log.d("NavigationFragment", e.message)

                    }
                }

            }
        }
    }

    val menu: MutableLiveData<Map<Int, MenuItem>> = object: MutableLiveData<Map<Int, MenuItem>>() {}

    fun fetchMenuItem(): LiveData<Map<Int, MenuItem>> {
        return object: LiveData<Map<Int, MenuItem>>() {
            override fun onActive() {
                super.onActive()

                RetrofitBuilder.apiService.getMenuItems(1, token.value?.toString()).enqueue(object: Callback< List<MenuItem>> {
                    override fun onResponse(
                        call: Call<List<MenuItem>>,
                        response: Response<List<MenuItem>>
                    ) {
                        if (response.isSuccessful) {
                            val map = mutableMapOf<Int, MenuItem>()
                            response.body()?.forEach {
                                map[it.id] = it
                            }
                            menu.postValue(map)
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
        }
    }



}