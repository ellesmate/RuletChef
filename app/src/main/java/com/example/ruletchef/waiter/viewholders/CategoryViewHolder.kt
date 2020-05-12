package com.example.ruletchef.waiter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R


class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val view: View = itemView
    var categoryImage: ImageView = itemView.findViewById(R.id.wtr_category_image)
    var categoryTitle: TextView = itemView.findViewById(R.id.wtr_category_title)

}