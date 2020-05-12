package com.example.ruletchef.waiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.Order
import com.example.ruletchef.waiter.viewholders.OrderViewHolder


class OrderRecyclerViewAdapter(private val orderList: List<Order>) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.wtr_order_card, parent, false)

        return OrderViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        if (position < orderList.size) {
            val order = orderList[position]

            holder.addressTextView.text = order.address

            println(order.items)

            val adapter = OrderItemRecyclerViewAdapter(order.items)
            holder.recyclerView.adapter = adapter
        }
    }
}