package com.luiz.bankapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.luiz.bankapp.model.Conta
import kotlinx.coroutines.tasks.await

object FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun login(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun register(email: String, password: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val uid = auth.currentUser?.uid ?: return false
            val conta = Conta(userId = uid, saldo = 0.0)
            db.collection("contas").document(uid).set(conta).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getSaldo(): Double {
        val uid = auth.currentUser?.uid ?: return 0.0
        val doc = db.collection("contas").document(uid).get().await()
        return doc.toObject(Conta::class.java)?.saldo ?: 0.0
    }

    suspend fun depositar(valor: Double) {
        val uid = auth.currentUser?.uid ?: return
        val ref = db.collection("contas").document(uid)
        val saldoAtual = getSaldo()
        ref.update("saldo", saldoAtual + valor).await()
    }

    suspend fun sacar(valor: Double) {
        val uid = auth.currentUser?.uid ?: return
        val saldoAtual = getSaldo()
        if (saldoAtual >= valor) {
            val ref = db.collection("contas").document(uid)
            ref.update("saldo", saldoAtual - valor).await()
        }
    }
}
