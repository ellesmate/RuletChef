package com.example.ruletchef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.models.Order
import com.example.ruletchef.viewmodels.NavigationViewModel
import kotlinx.android.synthetic.main.chf_navigation_fragment.view.*


class NavigationFragment : Fragment() {

    lateinit var viewModel: NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = NavigationViewModel()

    }

    var currentOrderFragment: CurrentOrderFragment? = null
    var activeOrdersFragment: ActiveOrdersFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.chf_navigation_fragment, container, false)

//        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
//            .replace(R.id.navigation_container, CurrentOrderFragment()).commit()

        view.navigation.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment? = null
            when(it.itemId) {
                R.id.action_search -> {
                    if (currentOrderFragment == null) {
                        currentOrderFragment = CurrentOrderFragment(viewModel)
                    }
                    selectedFragment = currentOrderFragment

                }
                R.id.action_settings -> {
                    if (activeOrdersFragment == null) {
                        activeOrdersFragment = ActiveOrdersFragment(viewModel)
                    }
                    selectedFragment = activeOrdersFragment
                }
            }

            if(selectedFragment != null) {
                (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.navigation_container, selectedFragment).commit()

                return@setOnNavigationItemSelectedListener true
            }
            false
        }

        view.navigation.selectedItemId = R.id.action_search




        return view
    }
}