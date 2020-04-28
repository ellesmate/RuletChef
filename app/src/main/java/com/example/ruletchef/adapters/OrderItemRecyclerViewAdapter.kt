package com.example.ruletchef.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.viewhoders.OrderItemViewHolder
import com.squareup.picasso.Picasso

class OrderItemRecyclerViewAdapter(private val orderItemList: List<OrderItem>) : RecyclerView.Adapter<OrderItemViewHolder>() {
    override fun getItemCount(): Int {
        return orderItemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.chf_order_item, parent, false)

        return OrderItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        if (position < orderItemList.size) {
            val item = orderItemList[position]
            holder.orderItemType.text = item.type
            holder.orderItemAmount.text = item.amount.toString()
            holder.orderItemWishes.text = item.wishes
            holder.orderItemTime.text = "7 min ago"

            Picasso.get().load(item.image).into(holder.orderItemImage)
        }

    }
}
