package com.example.ruletchef

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.chf_login_fragment.*
import kotlinx.android.synthetic.main.chf_login_fragment.view.*

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.chf_login_fragment, container, false)

        view.next_button.setOnClickListener {
            if (!isPasswordValid(password_edit_text.text!!)) {
                password_text_input.error = getString(R.string.chf_error_password)
            } else {
                password_text_input.error = null

                (activity as NavigationHost).navigateTo(NavigationFragment(), false)
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