package com.luiz.bankapp.controller

import com.luiz.bankapp.repository.FirebaseRepository

class ContaController {
    suspend fun getSaldo() = FirebaseRepository.getSaldo()
    suspend fun depositar(valor: Double) = FirebaseRepository.depositar(valor)
    suspend fun sacar(valor: Double) = FirebaseRepository.sacar(valor)
}