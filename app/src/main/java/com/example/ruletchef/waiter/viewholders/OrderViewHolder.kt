package com.example.ruletchef.waiter.viewholders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import kotlinx.android.synthetic.main.wtr_order_card.view.*

class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var payedButton: Button = itemView.findViewById(R.id.wtr_payed_button)
    var addressTextView: TextView = itemView.findViewById(R.id.wtr_order_card_address)
    var recyclerView: RecyclerView = itemView.findViewById(R.id.wtr_order_recycler_view)
}