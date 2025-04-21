package com.luiz.bankapp.view
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luiz.bankapp.controller.ContaController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContaViewModel : ViewModel() {
    private val controller = ContaController()

    private val _saldo = MutableStateFlow(0.0)
    val saldo = _saldo.asStateFlow()

    fun carregarSaldo() {
        viewModelScope.launch {
            _saldo.value = controller.getSaldo()
        }
    }

    fun depositar(valor: Double) {
        viewModelScope.launch {
            controller.depositar(valor)
            carregarSaldo()
        }
    }

    fun sacar(valor: Double) {
        viewModelScope.launch {
            controller.sacar(valor)
            carregarSaldo()
        }
    }
}
