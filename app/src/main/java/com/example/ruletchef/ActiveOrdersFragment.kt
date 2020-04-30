package com.example.ruletchef

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruletchef.adapters.OrderCardRecyclerViewAdapter
import com.example.ruletchef.viewmodels.ActiveOrdersViewModel
import kotlinx.android.synthetic.main.chf_active_orders_fragment.view.*


class ActiveOrdersFragment : Fragment() {

    lateinit var viewModel : ActiveOrdersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ActiveOrdersViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.chf_active_orders_fragment, container, false)

        view.active_order_recycler_view.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.active_order_recycler_view.layoutManager = layoutManager

        viewModel.orders.observe(this, Observer {
            Log.d("NavigationFragment", "Loaded!!!")

            val adapter = OrderCardRecyclerViewAdapter(it)
            view.active_order_recycler_view.adapter = adapter
        })

        return view
    }
}