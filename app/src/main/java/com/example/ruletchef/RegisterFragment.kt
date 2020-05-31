package com.example.ruletchef

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.ruletchef.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.chf_register_fragment.view.*


class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.chf_register_fragment, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        view.app_bar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        view.next_button.setOnClickListener {
            val email = view.email_edit_text.text
            val username = view.username_edit_text.text
            val password = view.password_edit_text.text
            val password2 = view.password2_edit_text.text

            if (email.isNullOrEmpty()) {
                return@setOnClickListener
            }
            if (username.isNullOrEmpty()) {
                return@setOnClickListener
            }
            if (password.isNullOrEmpty()) {
                return@setOnClickListener
            }
            if (password2.isNullOrEmpty()) {
                return@setOnClickListener
            }
            if (password.toString() != password2.toString()) {
                return@setOnClickListener
            }

            viewModel.register(
                email.toString(),
                username.toString(),
                password.toString(),
                password2.toString()
            )

            activity?.onBackPressed()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = RegisterViewModel()
    }

}
