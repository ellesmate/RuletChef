package com.example.ruletchef.waiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.MenuItem
import com.example.ruletchef.waiter.viewholders.CartItemViewHolder
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import com.squareup.picasso.Picasso

class CartRecyclerViewAdapter(val cartList: List<Pair<MenuItem, Int>>, val viewModel: WaiterNavigationViewModel)
    : RecyclerView.Adapter<CartItemViewHolder>() {
    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {

        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.wtr_cart_item, parent, false)

        return CartItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        if (position < cartList.size) {
            val (item, amount) = cartList[position]

            holder.itemAmount.text = "Amount $amount"
            holder.itemCost.text = "Cost: ${item.price/100}.${item.price%100}"
            holder.itemTitle.text = item.type

            Picasso.get().load(item.image).into(holder.itemImage)

            holder.view.setOnClickListener {
                viewModel.cart.value?.remove(item)
                viewModel.cart.value = viewModel.cart.value
            }
        }
    }
}