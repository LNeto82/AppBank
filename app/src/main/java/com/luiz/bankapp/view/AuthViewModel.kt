package com.luiz.bankapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luiz.bankapp.repository.FirebaseRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var onLoginSuccess: (() -> Unit)? = null
    var onFail: ((String) -> Unit)? = null

    fun login(email: String, senha: String) {
        viewModelScope.launch {
            val result = FirebaseRepository.login(email, senha)
            if (result) onLoginSuccess?.invoke() else onFail?.invoke("Erro no login")
        }
    }

    fun register(email: String, senha: String) {
        viewModelScope.launch {
            val result = FirebaseRepository.register(email, senha)
            if (result) onLoginSuccess?.invoke() else onFail?.invoke("Erro no cadastro")
        }
    }
}