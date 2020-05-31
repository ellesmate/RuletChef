package com.example.ruletchef.waiter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.models.*
import com.example.ruletchef.repository.Repository
import com.example.ruletchef.waiter.net.WaiterWSListener
import okhttp3.WebSocket


class WaiterNavigationViewModel : ViewModel() {
    var menu: MutableLiveData<Map<Int, MenuItem>> = Repository.fetchMenuMap()

    var orders: MutableLiveData<MutableList<Order>> = Transformations
        .switchMap(menu) {
            Repository.fetchOrders(it, State.DELIVERING) // DELIVERING OR PAYING
        } as MutableLiveData<MutableList<Order>>

    var categories: MutableLiveData<List<Category>> = Repository.fetchCategories()
    val category: MutableLiveData<Category> = MutableLiveData()
    val menuItems: MutableLiveData<MutableList<MenuItem>> = Transformations
        .switchMap(category) {
            Repository.fetchMenuItems(it)
        } as MutableLiveData<MutableList<MenuItem>>

    val cart: MutableLiveData<MutableMap<MenuItem, Int>> = MutableLiveData(mutableMapOf())

    var ws: WebSocket = RetrofitBuilder.newWebSocket("ws/hall/", WaiterWSListener(this))

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

        var values = orders.value
        if (values != null) {
            var i = 0
            while (i < values.size) {
                if (values[i].id == order.id) {
                    values[i] = order
                    break
                }
                i++
            }
            if (i >= values.size) {
                values.add(order)
            }
        } else {
            values = mutableListOf(order)
        }

        orders.postValue(values)
    }

    fun payOrder(order: Order) {
        orders.value?.forEach {
            if (it.id == order.id) {
                if (it.items.all {orderItem ->
                        orderItem.state == State.DONE
                    }) {
                    order.state = State.DONE
                    val message = WaiterWSListener.Message(null, null, order)
                    val json = RetrofitBuilder.gson.toJson(message)
                    ws.send(json)
                }
                return@forEach
            }
        }
    }

    fun deliverOrderItem(orderItem: OrderItem) {
        if (orderItem.state == State.DELIVERING) {
            orderItem.state = State.DONE
            val message = WaiterWSListener.Message(null, orderItem, null)
            val json = RetrofitBuilder.gson.toJson(message)
            ws.send(json)
        }
    }

    fun receiveOrderItem(orderItem: OrderItem) {
        var item: OrderItem? = null
        orders.value?.forEach {order ->
            if (order.id == orderItem.orderId) {
                order.items.forEach {
                    if (it.id == orderItem.id) {
                        it.state = orderItem.state
                        item = it
                    }
                }
                if (item != null) {
                    order.items.remove(item!!)
                }
            }
        }

        orders.value = orders.value
    }

    fun receiveOrder(order: Order) {
        var select: Order? = null
        orders.value?.forEach {
            if (it.id == order.id) {
                if (it.items.all {orderItem ->
                        orderItem.state == State.DONE
                }) {
                    it.state = order.state
                }
                select = it
            }
        }

        if (select != null) {
            orders.value?.remove(select!!)
            orders.value = orders.value
        }
    }
}
