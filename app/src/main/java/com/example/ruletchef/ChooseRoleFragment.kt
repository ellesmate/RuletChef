package com.example.ruletchef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.wrk_choose_role.view.*


class ChooseRoleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wrk_choose_role, container, false)

        view.wrk_chef_textview.setOnClickListener {
            (activity as NavigationHost).navigateTo(NavigationFragment(), false)
        }

        view.wrk_courier_textview.setOnClickListener {

        }

        view.wrk_waiter_textview.setOnClickListener {

        }

        return view
    }

}