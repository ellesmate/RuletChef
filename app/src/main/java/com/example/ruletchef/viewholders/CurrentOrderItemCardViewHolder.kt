package com.example.ruletchef.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R

class CurrentOrderItemCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var orderItemLayout: View = itemView
    var orderItemName: TextView = itemView.findViewById(R.id.current_order_name)
    var orderItemAmount: TextView = itemView.findViewById(R.id.current_order_amount)
    var orderItemTime: TextView = itemView.findViewById(R.id.current_order_time)
    var orderItemImage: ImageView = itemView.findViewById(R.id.current_order_image)
}
