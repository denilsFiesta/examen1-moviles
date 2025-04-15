package com.ucb.testmovuno.myfavoritebooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Book
import com.ucb.usecases.GetMyFavoriteBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFavoriteBooksViewModel @Inject constructor(
    private val getMyFavoriteBooks: GetMyFavoriteBooks
) : ViewModel() {

    private val _favoriteBooks = MutableStateFlow<List<Book>>(emptyList())
    val favoriteBooks: StateFlow<List<Book>> = _favoriteBooks

    fun loadFavorites() {
        viewModelScope.launch {
            _favoriteBooks.value = getMyFavoriteBooks.invoke()
        }
    }
}

