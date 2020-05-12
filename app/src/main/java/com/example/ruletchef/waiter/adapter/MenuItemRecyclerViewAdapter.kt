package com.example.ruletchef.waiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.MenuItem
import com.example.ruletchef.waiter.viewholders.MenuItemViewHolder
import com.squareup.picasso.Picasso


class MenuItemRecyclerViewAdapter(val menuItemList: List<MenuItem>) : RecyclerView.Adapter<MenuItemViewHolder>() {
    override fun getItemCount(): Int {
        return menuItemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.wtr_menu_item, parent, false)

        return MenuItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        if (position < menuItemList.size) {
            val menuItem = menuItemList[position]

            holder.itemTitle.text = menuItem.type
            holder.itemCost.text = "Cost: ${menuItem.price / 100}.${menuItem.price%100}$"

            Picasso.get().load(menuItem.image).into(holder.itemImage)
        }
    }
}