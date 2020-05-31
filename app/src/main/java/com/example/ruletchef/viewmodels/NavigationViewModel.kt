package com.example.ruletchef.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.api.WSListener
import com.example.ruletchef.models.*
import com.example.ruletchef.repository.Repository
import okhttp3.WebSocket

class NavigationViewModel : ViewModel() {
    var menu: MutableLiveData<Map<Int, MenuItem>> = Repository.fetchMenuMap()
    var orders: MutableLiveData<MutableList<Order>> = Transformations
        .switchMap(menu) {
            Repository.fetchOrders(it, State.NEW)
        } as MutableLiveData<MutableList<Order>>

    var currentOrders: LiveData<MutableList<OrderItem>> = Transformations
        .map(orders) {orders->
            val list = mutableListOf<OrderItem>()
            orders.forEach { order ->
                order.items.forEach { orderItem ->
                    if (orderItem.cook == Repository.me?.id?:0) {
                        list.add(orderItem)
                    }
                }
            }
            list
        }

    fun pushBack(order: Order) {

        val menuItemMap = menu.value

        if(menuItemMap == null) {
            return
        }

        order.items.forEach { item ->
            val menuItem = menuItemMap[item.menuItemId]
            item.apply {
                image = menuItem!!.image
                description = menuItem.description
                type = menuItem.type
            }
        }
        orders.apply {
            if (value == null) {
                value = mutableListOf(order)
            } else {
                value?.add(order)
                value = value
            }
        }
    }

    fun applyUpdate(update: Update) {
        orders.value?.apply {
            this.forEach {
                if (it.id == update.orderId) {
                    it.items.forEach {orderItem ->
                        if (orderItem.id == update.orderItemId) {
                            println(orderItem)

                            orderItem.apply{
                                cook = update.cook
                                state = update.state
                            }

                            if (update.state == State.DELIVERING) {
                                if (it.items.size == 1) {
                                    ((it.items) as MutableList<OrderItem>).remove(orderItem)
                                    this.remove(it)
                                } else {
                                    ((it.items) as MutableList<OrderItem>).remove(orderItem)
                                }

                            }
                            orders.value = orders.value
                            return
                        }
                    }
                    return@forEach
                }
            }
        }
    }

    fun takeOrderItem(orderItem: OrderItem) {
        val myId = Repository.me?.id
        if (myId == null) {
            println("error 'takeOrderItem id is null'")
            return
        }
        if (orderItem.cook != null && orderItem.cook != myId) {
            return
        }

        val state: State = if(orderItem.cook == myId) State.DELIVERING else State.COOKING
        val update = Update(orderItem.id, orderItem.orderId, myId, state)
        val message = WSListener.Message(null, update)
        val json = RetrofitBuilder.gson.toJson(message)
        ws.send(json)
    }


    fun getMenu() {
        menu = Repository.fetchMenuMap()
    }

    var ws: WebSocket = RetrofitBuilder.newWebSocket("ws/kitchen/", WSListener(this))
    fun create() {
        ws = RetrofitBuilder.newWebSocket("ws/kitchen/",WSListener(this))
    }

}