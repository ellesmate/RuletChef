package com.example.ruletchef.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ruletchef.repository.Repository

class ActiveOrdersViewModel : ViewModel() {
    val orders = Repository.fetchOrders()
}