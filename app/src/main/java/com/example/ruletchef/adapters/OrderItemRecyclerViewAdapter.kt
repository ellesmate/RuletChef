package com.example.ruletchef.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.models.State
import com.example.ruletchef.repository.Repository
import com.example.ruletchef.viewhoders.OrderItemViewHolder
import com.example.ruletchef.viewmodels.NavigationViewModel
import com.squareup.picasso.Picasso

class OrderItemRecyclerViewAdapter(private val orderItemList: List<OrderItem>, private val viewModel: NavigationViewModel) : RecyclerView.Adapter<OrderItemViewHolder>() {
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
            holder.orderItemAmount.text = "Amount: ${item.amount}"
            holder.orderItemWishes.text = item.wishes
            holder.orderItemTime.text = "7 min ago"

            when(item.state) {
                State.NEW -> {
                    holder.orderItemLayout.setBackgroundResource(R.color.colorAccent)
                }
                State.COOKING -> {
                    if (item.cook == Repository.me) {
                        holder.orderItemLayout.setBackgroundResource(R.color.design_default_color_primary_variant)
                    } else {
                        holder.orderItemLayout.setBackgroundResource(R.color.design_default_color_secondary)
                    }
                }
                State.DELIVERING -> {
                    holder.orderItemLayout.setBackgroundResource(R.color.toolbarIconColor)

                }
                State.DONE -> {
                    holder.orderItemLayout.setBackgroundResource(R.color.toolbarIconColor)

                }
            }

            holder.orderItemLayout.setOnClickListener {
                viewModel.takeOrderItem(item)
            }

            Picasso.get().load(item.image)
                .resize(100, 100)
                .centerCrop()
                .into(holder.orderItemImage)
        }

    }
}
