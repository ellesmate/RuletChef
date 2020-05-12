package com.example.ruletchef.waiter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ruletchef.R
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import kotlinx.android.synthetic.main.wtr_navigation_fragment.view.*

class WaiterNavigationFragment : Fragment() {


    lateinit var viewModel: WaiterNavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = WaiterNavigationViewModel()
    }

    var ordersFragment: OrdersFragment? = null
    var menuFragment: MenuFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wtr_navigation_fragment, container, false)

        view.wtr_bottom_navigation.setOnNavigationItemSelectedListener {

            var selectedFragment: Fragment? = null
            when(it.itemId) {
                R.id.wtr_action_orders -> {
                    if (ordersFragment == null) {
                        ordersFragment = OrdersFragment(viewModel)
                    }
                    selectedFragment = ordersFragment

                }
                R.id.wtr_action_menu -> {
                    if (menuFragment == null) {
                        menuFragment = MenuFragment(viewModel)
                    }
                    selectedFragment = menuFragment
                }
                R.id.wtr_action_settings -> {

                }
            }

            if(selectedFragment != null) {
                (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.navigation_container, selectedFragment).commit()

                return@setOnNavigationItemSelectedListener true
            }
//            true
            false
        }

        view.wtr_bottom_navigation.selectedItemId = R.id.wtr_action_orders

        return view
    }
}