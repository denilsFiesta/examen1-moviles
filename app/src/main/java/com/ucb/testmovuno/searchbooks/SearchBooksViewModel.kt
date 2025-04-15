package com.ucb.testmovuno.searchbooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Book
import com.ucb.usecases.SearchBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchBooksViewModel : ViewModel(){
    val searchBooks = SearchBooks()
    private val _searchState = MutableStateFlow<List<Book>>(emptyList())
    val searchState: StateFlow<List<Book>> = _searchState
    fun searchBooks(toSearch: String) {
        _searchState.value = searchBooks.invoke(toSearch)
    }
}