package com.example.ruletchef.waiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.models.State
import com.example.ruletchef.waiter.viewholders.OrderItemViewHolder
import com.example.ruletchef.waiter.viewholders.OrderViewHolder
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import com.squareup.picasso.Picasso

class OrderItemRecyclerViewAdapter(val orderItemList: List<OrderItem>, val viewModel: WaiterNavigationViewModel) : RecyclerView.Adapter<OrderItemViewHolder>() {
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

            if (orderItem.state != State.DELIVERING) {
                holder.view.setBackgroundResource(R.color.colorPrimary)
            }


            holder.view.setOnClickListener {
                viewModel.deliverOrderItem(orderItem)
            }

            Picasso.get()
                .load(orderItem.image)
                .resize(200, 200)
                .centerCrop()
                .into(holder.imageView)
        }

    }
}
