package com.example.ruletchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.Order
import com.example.ruletchef.viewhoders.OrderCardViewHolder

class OrderCardRecyclerViewAdapter(private val orderList: List<Order>) : RecyclerView.Adapter<OrderCardViewHolder>() {
    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.chf_order_card, parent, false)

        return OrderCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: OrderCardViewHolder, position: Int) {
        if (position < orderList.size) {
            val order = orderList[position]
            val adapter = OrderItemRecyclerViewAdapter(order.items)
//            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            holder.orderRecyclerView.layoutManager = layoutManager
            holder.orderRecyclerView.adapter = adapter
        }
    }
}