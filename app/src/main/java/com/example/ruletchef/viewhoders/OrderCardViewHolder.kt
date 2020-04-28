package com.example.ruletchef.viewhoders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R

class OrderCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var orderRecyclerView: RecyclerView = itemView.findViewById(R.id.order_recycler_view)
}