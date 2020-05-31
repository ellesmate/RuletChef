package com.example.ruletchef.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ruletchef.repository.Repository

class RegisterViewModel : ViewModel() {
    fun register(email: String, username: String, password: String, password2: String) {
        Repository.register(email, username, password, password2)
    }
}
