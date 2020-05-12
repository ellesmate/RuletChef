package com.example.ruletchef.waiter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ruletchef.R
import com.example.ruletchef.decoration.GridItemDecoration
import com.example.ruletchef.waiter.adapter.CategoryRecyclerViewAdapter
import com.example.ruletchef.waiter.adapter.MenuItemRecyclerViewAdapter
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.wtr_menu_fragment.view.*
import kotlinx.android.synthetic.main.wtr_sheet_menu_items.*
import kotlinx.android.synthetic.main.wtr_sheet_menu_items.view.*


class MenuFragment(val viewModel: WaiterNavigationViewModel) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.categories.observe(this, Observer {
            println(it)
            val adapter = CategoryRecyclerViewAdapter(it, viewModel)
            this.view?.wtr_category_recycler_view?.adapter = adapter
        })

        viewModel.menuItems.observe(this, Observer {
            println(it)
            val adapter = MenuItemRecyclerViewAdapter(it)
            this.view!!.wtr_menu_sheet!!.wtr_menu_item_recycler_view!!.adapter = adapter
        })

        viewModel.category.observe(this, Observer {
            println(it)
            if (view != null) {
                BottomSheetBehavior.from((view as View).wtr_menu_sheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wtr_menu_fragment, container, false)

        view.wtr_category_recycler_view.setHasFixedSize(true)
        view.wtr_category_recycler_view.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        view.wtr_category_recycler_view.addItemDecoration(GridItemDecoration(30, 26))

        return view
    }
}