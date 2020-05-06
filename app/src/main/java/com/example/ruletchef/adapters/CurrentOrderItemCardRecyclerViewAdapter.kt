package com.example.ruletchef.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.viewhoders.CurrentOrderItemCardViewHolder
import com.example.ruletchef.viewhoders.OrderItemViewHolder
import com.example.ruletchef.viewmodels.NavigationViewModel
import com.squareup.picasso.Picasso

class CurrentOrderItemCardRecyclerViewAdapter(private val orderItemList: List<OrderItem>, private val viewModel: NavigationViewModel)
    : RecyclerView.Adapter<CurrentOrderItemCardViewHolder>() {
    override fun getItemCount(): Int {
        return orderItemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentOrderItemCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.chf_current_order_card, parent, false)

        return CurrentOrderItemCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CurrentOrderItemCardViewHolder, position: Int) {

        if (position < orderItemList.size) {
            val item = orderItemList[position]

            holder.orderItemAmount.text = "Amount ${item.amount}"
            holder.orderItemName.text = item.type
            holder.orderItemTime.text = "5 min ago"

            holder.orderItemLayout.setOnClickListener {
                viewModel.takeOrderItem(item)
            }

            Picasso.get().load(item.image)
                .resize(400, 400)
                .centerCrop()
                .into(holder.orderItemImage)
        }
    }
}