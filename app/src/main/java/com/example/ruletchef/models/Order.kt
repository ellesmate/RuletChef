package com.example.ruletchef.models

import java.util.*


data class Order (
    val orderTime: Date,
    val items: List<OrderItem>
)


