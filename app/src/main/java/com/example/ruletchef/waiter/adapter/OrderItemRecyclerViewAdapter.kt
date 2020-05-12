package com.example.ruletchef.waiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.waiter.viewholders.OrderItemViewHolder
import com.example.ruletchef.waiter.viewholders.OrderViewHolder
import com.squareup.picasso.Picasso

class OrderItemRecyclerViewAdapter(val orderItemList: List<OrderItem>) : RecyclerView.Adapter<OrderItemViewHolder>() {
    override fun getItemCount(): Int {
        return orderItemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.wtr_order_item, parent, false)

        return OrderItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        if (position < orderItemList.size) {
            val orderItem = orderItemList[position]

            holder.typeTextView.text = orderItem.type

            Picasso.get()
                .load(orderItem.image)
                .resize(200, 200)
                .centerCrop()
                .into(holder.imageView)
        }

    }
}
