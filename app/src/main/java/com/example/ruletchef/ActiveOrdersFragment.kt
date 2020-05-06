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
import com.example.ruletchef.models.Order
import com.example.ruletchef.viewmodels.NavigationViewModel
import kotlinx.android.synthetic.main.chf_active_orders_fragment.view.*


class ActiveOrdersFragment(val navViewModel: NavigationViewModel) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observer = Observer {it:List<Order> ->
            Log.d("NavigationFragment", "Loaded!!!")

            val adapter = OrderCardRecyclerViewAdapter(it, navViewModel)
            this.view?.active_order_recycler_view?.adapter = adapter
        }
        navViewModel.orders.observe(this, observer)
    }

    lateinit var observer: Observer<List<Order>>
//    var myView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.chf_active_orders_fragment, container, false)

        view.active_order_recycler_view.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.active_order_recycler_view.layoutManager = layoutManager


//        navViewModel.orders.removeObservers(this)
//        observer = Observer {it:List<Order> ->
//            Log.d("NavigationFragment", "Loaded!!!")
//
//            val adapter = OrderCardRecyclerViewAdapter(it)
//            view?.active_order_recycler_view?.adapter = adapter
//        }

//        navViewModel.orders.observe(this, observer)

        return view
    }
}