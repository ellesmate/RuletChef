package com.example.ruletchef.waiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.Order
import com.example.ruletchef.waiter.viewholders.OrderViewHolder
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel


class OrderRecyclerViewAdapter(private val orderList: List<Order>, val viewModel: WaiterNavigationViewModel) : RecyclerView.Adapter<OrderViewHolder>() {

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

            holder.payedButton.setOnClickListener {
                viewModel.payOrder(order)
            }

            val adapter = OrderItemRecyclerViewAdapter(order.items, viewModel)
            holder.recyclerView.adapter = adapter
        }
    }
}