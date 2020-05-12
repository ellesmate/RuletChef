package com.example.ruletchef.waiter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R

class OrderItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var imageView: ImageView = itemView.findViewById(R.id.wtr_order_item_image)
    var typeTextView: TextView = itemView.findViewById(R.id.wtr_order_item_name)
}
