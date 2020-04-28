package com.example.ruletchef.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.example.ruletchef.models.Order
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.models.State
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.util.*


object Repository {



    fun fetchOrders() : LiveData<List<Order>> {

        return object: LiveData<List<Order>>() {
            override fun onActive() {
                super.onActive()
                Log.d("NavigationFragment", "Start Load")

                GlobalScope.launch {
                    delay(10000)
                    val link = "https://picsum.photos/200/300"
                    val desc = "Something eatable"
                    val type = "Food"
                    val wishes = "No sugar"

                    val normal = Order(Date(2020, 4, 27, 17, 30, 0),
                        listOf<OrderItem>(
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY),
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY),
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY),
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY)
                        ))


                    val small = Order(Date(2020, 4, 27, 17, 40, 0),
                        listOf<OrderItem>(
                            OrderItem(link, "Small order", type, (1..10).random(), wishes, State.EMPTY)
                        ))

                    val big = Order(Date(2020, 4, 27, 17, 45, 0),
                        listOf<OrderItem>(
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY),
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY),
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY),
                            OrderItem(link, desc, type, (1..10).random(), wishes, State.EMPTY)
                        ))

                    withContext(Dispatchers.Main) {
                        value = listOf<Order>(normal, small, big)

                    }
                }

            }
        }
    }
}