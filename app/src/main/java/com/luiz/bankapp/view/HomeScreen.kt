package com.luiz.bankapp.view
import androidx.compose.ui.Modifier


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luiz.bankapp.view.ContaViewModel // ajuste o caminho se necessário
import com.luiz.bankapp.ui.theme.Vinho

@Composable
fun HomeScreen() {
    val viewModel: ContaViewModel = viewModel()
    val saldo by viewModel.saldo.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarSaldo()
    }

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Saldo Atual:", style = MaterialTheme.typography.headlineSmall)
        Text("R$ $saldo", style = MaterialTheme.typography.headlineMedium, color = Vinho)

        Spacer(Modifier.height(24.dp))

        Button(onClick = { viewModel.depositar(100.0) }) {
            Text("Depositar R$100")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = { viewModel.sacar(50.0) }) {
            Text("Sacar R$50")
        }
    }
}
