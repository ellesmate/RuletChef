package com.example.ruletchef

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ruletchef.repository.Repository
import com.example.ruletchef.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.chf_login_fragment.*
import kotlinx.android.synthetic.main.chf_login_fragment.view.*

class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = LoginViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.chf_login_fragment, container, false)

        view.next_button.setOnClickListener {

            val email = email_edit_text.text!!
            val password = password_edit_text.text!!


            if (!isPasswordValid(password_edit_text.text!!)) {
                password_text_input.error = getString(R.string.chf_error_password)
            } else {
                password_text_input.error = null

                viewModel.auth(email.toString(), password.toString()).observe(this, Observer {

                    if (it) {
                        Repository.fetchMenuItem().observe(this, Observer {
                            (activity as NavigationHost).navigateTo(NavigationFragment(), false)
                        })
                    } else {
                        Snackbar.make(view, "Auth Error. Please try again.", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                })

            }
        }

        view.password_edit_text.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(password_edit_text.text!!)) {
                password_text_input.error = null
            }
            false
        }

        return view
    }

    private fun isPasswordValid(text: Editable?) : Boolean {
        return text != null && text.length >= 8
    }
}