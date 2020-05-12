package com.example.ruletchef.waiter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruletchef.R
import com.example.ruletchef.waiter.adapter.OrderRecyclerViewAdapter
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import kotlinx.android.synthetic.main.wtr_orders_fragment.view.*

class OrdersFragment(val viewModel: WaiterNavigationViewModel) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.orders.observe(this, Observer {
            val adapter = OrderRecyclerViewAdapter(it)
            this.view?.wtr_orders_recycler_view?.adapter = adapter
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.wtr_orders_fragment, container, false)

//        view.wtr_orders_recycler_view.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.wtr_orders_recycler_view.layoutManager = layoutManager

        return view
    }
}