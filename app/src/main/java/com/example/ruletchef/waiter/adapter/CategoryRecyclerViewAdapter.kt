package com.example.ruletchef.waiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.models.Category
import com.example.ruletchef.waiter.viewholders.CategoryViewHolder
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import com.squareup.picasso.Picasso


class CategoryRecyclerViewAdapter(val categoryList: List<Category>, val viewModel: WaiterNavigationViewModel)
    : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.wtr_category_card, parent, false)

        return CategoryViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (position < categoryList.size) {
            val category = categoryList[position]
            holder.categoryTitle.text = category.name


            holder.view.setOnClickListener {
                viewModel.category.postValue(category)
            }

            Picasso.get().load(category.image).into(holder.categoryImage)
        }
    }
}
