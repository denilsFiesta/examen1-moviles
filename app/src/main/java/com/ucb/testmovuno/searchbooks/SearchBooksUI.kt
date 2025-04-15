package com.ucb.testmovuno.searchbooks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import com.ucb.domain.Book


@Composable
fun SearchBooksUI(onBack: () -> Unit) {
    val viewModel: SearchBooksViewModel = hiltViewModel()

    var query by remember { mutableStateOf("") }
    val state by viewModel.flow.collectAsState()

    val favoriteBooks = remember { mutableStateListOf<Book>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Botón Volver
        Button(
            onClick = onBack,
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("📚 ${book.title} (${book.publicationYear})", style = MaterialTheme.typography.titleMedium)
                                    Text("Autores: ${book.authors.joinToString()}", style = MaterialTheme.typography.bodyMedium)
                                }

                                val isFavorite = favoriteBooks.contains(book)

                                IconButton(onClick = {
                                    if (!isFavorite) {
                                        viewModel.addToFavorites(book)
                                        favoriteBooks.add(book)
                                    } else {
                                        favoriteBooks.remove(book)
                                        // hago el eliminar??
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = if (isFavorite) "Remover de favoritos" else "Agregar a favoritos"
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
