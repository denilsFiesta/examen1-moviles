package com.ucb.testmovuno.myfavoritebooks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyFavoriteBooksUI() {
    val viewModel: MyFavoriteBooksViewModel = hiltViewModel()
    val favoriteBooks by viewModel.favoriteBooks.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { /* TODO: Navegación hacia atrás */ },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("← Volver")
        }

        Text(
            text = "Mis libros favoritos",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(favoriteBooks) { book ->
                Card(modifier = Modifier.fillMaxWidth()) {
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

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorito"
                        )
                    }
                }
            }
        }
    }
}
