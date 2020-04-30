package com.example.ruletchef.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ruletchef.repository.Repository

class LoginViewModel : ViewModel() {

    val authing: MutableLiveData<Boolean> = MutableLiveData()

    fun auth(email: String, password: String) : LiveData<Boolean> {
        authing.postValue(true)
        return Repository.auth(email, password)
    }
}