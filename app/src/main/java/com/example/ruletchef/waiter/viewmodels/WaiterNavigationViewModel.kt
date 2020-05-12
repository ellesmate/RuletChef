package com.example.ruletchef.waiter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.models.Category
import com.example.ruletchef.models.MenuItem
import com.example.ruletchef.models.Order
import com.example.ruletchef.models.State
import com.example.ruletchef.repository.Repository


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
}