package com.example.ruletchef.livedata

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.models.MenuItem
import com.example.ruletchef.models.Order
import com.example.ruletchef.models.State
import com.example.ruletchef.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//interface PushbackableLiveData<Order> {
//    fun pushBack(order: Order) {
//
//        if (value == null) {
//            value = mutableListOf(order)
//        } else {
//            value?.add(order)
//            value = value
//        }
//    }
//}

class OrderLiveData(val menuItemMap: Map<Int, MenuItem>, val status: State) : MediatorLiveData<MutableList<Order>>(){
    private val TAG: String = "OrderLiveData"

    var isTouchable: Boolean = true

    fun fetch() {
        Log.d("NavigationFragment", "Start Load")
        Log.d("NavigationFragment", this.hashCode().toString())
        RetrofitBuilder.apiService.getOrders(1, Repository.token.value?.toString(), status)
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
                        Repository.handleResponseCode(response.code())
                    }
                }
                override fun onFailure(call: Call<MutableList<Order>>, t: Throwable) {
                    Log.d(TAG, "Fetch order error", t)
                }
            })
    }



//    override fun pushBack(order: Order) {
//        if (value == null) {
//            value = mutableListOf(order)
//        } else {
//            value?.add(order)
//            value = value
//        }
//
//    }

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