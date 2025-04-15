package com.ucb.testmovuno.searchbooks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.domain.Book

@Composable
fun SearchBooksUI() {
    val viewModel: SearchBooksViewModel = hiltViewModel()

    var query by remember { mutableStateOf("") }
    val state by viewModel.flow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Botón Volver
        Button(
            onClick = { /* TODO: Navegación hacia atrás */ },
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
                viewModel.search(query)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = query.isNotBlank()
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // UI según el estado
        when (val current = state) {
            is SearchBooksViewModel.BookState.Init -> {
                Text("Ingresa una búsqueda para comenzar.")
            }

            is SearchBooksViewModel.BookState.Loading -> {
                CircularProgressIndicator()
            }

            is SearchBooksViewModel.BookState.Successful -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(current.books) { book ->
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
    }
}
