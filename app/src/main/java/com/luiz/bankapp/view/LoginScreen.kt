package com.luiz.bankapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luiz.bankapp.view.AuthViewModel
import com.luiz.bankapp.ui.theme.Vinho
import com.luiz.bankapp.ui.theme.Preto
import com.luiz.bankapp.ui.theme.Branco

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    viewModel.onLoginSuccess = onLoginSuccess
    viewModel.onFail = {
        // Você pode usar Scaffold com Snackbar ou Toast aqui
        println("Falha no login") // temporário
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "BankApp",
            style = MaterialTheme.typography.headlineLarge,
            color = Vinho
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.login(email, senha) },
            colors = ButtonDefaults.buttonColors(containerColor = Vinho),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar", color = Branco)
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { viewModel.register(email, senha) },
            colors = ButtonDefaults.buttonColors(containerColor = Preto),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar", color = Branco)
        }
    }
}
