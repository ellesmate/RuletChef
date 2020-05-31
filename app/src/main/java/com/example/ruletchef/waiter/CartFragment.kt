package com.example.ruletchef.waiter

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ruletchef.R
import com.example.ruletchef.models.Order
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.models.State
import com.example.ruletchef.repository.Repository
import com.example.ruletchef.waiter.adapter.CartRecyclerViewAdapter
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import kotlinx.android.synthetic.main.wtr_cart_fragment.view.*

class CartFragment(val viewModel: WaiterNavigationViewModel) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.cart.observe(this, Observer {
            val adapter = CartRecyclerViewAdapter(it.toList(), viewModel)
            this.view?.wtr_cart_recycler_view?.adapter = adapter
        })

    }
    var address: Editable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.wtr_cart_fragment, container, false)
//        view.wtr_cart_recycler_view.
        var entity: Int = 0

        view.wtr_cart_checkout.setOnClickListener {
            val orderItems = viewModel.cart.value?.map {(menuItem, amount) ->
                val orderItem = OrderItem(0, amount, menuItem.id, 0, "", State.NEW, null)
                entity = menuItem.entityId
                orderItem
            } as MutableList<OrderItem>?
            if (orderItems != null) {
                val meId = Repository.me?.id
                if (meId != null) {
                    val order = Order(0, entity, null, false, orderItems, meId,
                        1, view.address_edit_text.editText?.text.toString(), State.NEW)

                    print("order create")
                    println(order)
                    Repository.createOrder(order)
                }
            }
            viewModel.cart.value = mutableMapOf()
        }

        if (address != null) {
            view.address_edit_text.editText?.text = address
        }

        return view
    }

    override fun onDestroyView() {
        address = view?.address_edit_text?.editText?.text
        super.onDestroyView()
    }
}