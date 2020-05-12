package com.example.ruletchef.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R

class OrderItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var orderItemView: View = itemView
    var orderItemLayout: View = itemView.findViewById(R.id.order_item_layout)
    var orderItemImage: ImageView = itemView.findViewById(R.id.order_item_image)
    var orderItemType: TextView = itemView.findViewById(R.id.order_item_type)
    var orderItemAmount: TextView = itemView.findViewById(R.id.order_item_amount)
    var orderItemTime: TextView = itemView.findViewById(R.id.order_item_time)
    var orderItemWishes: TextView = itemView.findViewById(R.id.order_item_wishes)
}
