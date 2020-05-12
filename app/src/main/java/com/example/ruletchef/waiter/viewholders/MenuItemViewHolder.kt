package com.example.ruletchef.waiter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import kotlinx.android.synthetic.main.wtr_menu_item.view.*


class MenuItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemImage: ImageView = itemView.findViewById(R.id.wtr_menu_item_image)
    var itemTitle: TextView = itemView.findViewById(R.id.wtr_menu_item_title)
    var itemCost: TextView = itemView.findViewById(R.id.wtr_menu_item_cost)
}