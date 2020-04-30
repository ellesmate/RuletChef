package com.example.ruletchef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.chf_navigation_fragment.view.*


class NavigationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.chf_navigation_fragment, container, false)

        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.navigation_container, CurrentOrderFragment()).commit()

        view.navigation.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment? = null
            when(it.itemId) {
                R.id.action_search -> {
                    selectedFragment = CurrentOrderFragment()
                }
                R.id.action_settings -> {
                    selectedFragment = ActiveOrdersFragment()
                }
            }

            if(selectedFragment != null) {
                (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.navigation_container, selectedFragment).commit()

                return@setOnNavigationItemSelectedListener true
            }
            false
        }




        return view
    }
}