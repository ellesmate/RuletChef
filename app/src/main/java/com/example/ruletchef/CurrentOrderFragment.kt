package com.example.ruletchef

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruletchef.adapters.CurrentOrderItemCardRecyclerViewAdapter
import com.example.ruletchef.adapters.OrderCardRecyclerViewAdapter
import com.example.ruletchef.models.Order
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.viewmodels.NavigationViewModel
import kotlinx.android.synthetic.main.chf_current_order_fragment.view.*


class CurrentOrderFragment(val navViewModel: NavigationViewModel) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observer = Observer {it:List<OrderItem> ->
            Log.d("NavigationFragment", "Loaded!!!")

            val adapter = CurrentOrderItemCardRecyclerViewAdapter(it, navViewModel)
            this.view?.current_order_recycler_view?.adapter = adapter
        }
        navViewModel.currentOrders.observe(this, observer)
    }

    lateinit var observer: Observer<List<OrderItem>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.chf_current_order_fragment, container, false)

        view.current_order_recycler_view.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.current_order_recycler_view.layoutManager = layoutManager

        return view
    }
}