package com.ucb.testmovuno.searchbooks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.domain.Book

@Composable
fun SearchBooksUI() {

    val viewModel: SearchBooksViewModel = viewModel()

    var query by remember { mutableStateOf("") }
    val results by viewModel.searchState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Botón Volver
        Button(
            onClick = { },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("← Volver")
        }

        // Campo de búsqueda
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar libro...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botón Buscar
        Button(
            onClick = {
                viewModel.searchBooks(query)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resultados en una LazyColumn
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(results) { book ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("📚 ${book.title} (${book.publicationYear})", style = MaterialTheme.typography.titleMedium)
                        Text("Autores: ${book.authors.joinToString()}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}