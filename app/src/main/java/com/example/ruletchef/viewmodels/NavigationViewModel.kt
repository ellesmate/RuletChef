package com.example.ruletchef.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ruletchef.repository.Repository

class NavigationViewModel : ViewModel() {
    val orders = Repository.fetchOrders()
}