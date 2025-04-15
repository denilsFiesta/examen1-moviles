package com.ucb.testmovuno.searchbooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Book
import com.ucb.usecases.SearchBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBooksViewModel @Inject constructor(
    private val searchBooks: SearchBooks
) : ViewModel() {

    sealed class BookState {
        object Init : BookState()
        object Loading : BookState()
        class Successful(val books: List<Book>) : BookState()
    }

    private val _flow = MutableStateFlow<BookState>(BookState.Init)
    val flow: StateFlow<BookState> = _flow

    fun search(query: String) {
        viewModelScope.launch {
            _flow.value = BookState.Loading
            val result = searchBooks.invoke(query)
            _flow.value = BookState.Successful(result)
        }
    }
}
