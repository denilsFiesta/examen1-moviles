package com.ucb.testmovuno.searchbooks

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import com.ucb.usecases.AddToMyFavorites
import com.ucb.usecases.SearchBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBooksViewModel @Inject constructor(
    private val searchBooks: SearchBooks,
    private val addToMyFavorites: AddToMyFavorites
) : ViewModel() {

    sealed class BookState {
        object Init : BookState()
        object Loading : BookState()
        data class Successful(val books: List<Book>) : BookState()
        data class Error(val message: String) : BookState()
    }

    private val _flow = MutableStateFlow<BookState>(BookState.Init)
    val flow: StateFlow<BookState> = _flow

    fun search(context: Context, query: String) {
        viewModelScope.launch {
            if (!isConnected(context)) {
                _flow.value = BookState.Error("No tienes conexión a Internet")
                return@launch
            }

            _flow.value = BookState.Loading

            val result = searchBooks.invoke(query)
            when (result) {
                is NetworkResult.Success -> {
                    val uniqueBooks = result.data.distinctBy { it.title to it.authors.joinToString() }
                    _flow.value = BookState.Successful(uniqueBooks)
                }

                is NetworkResult.Error -> {
                    _flow.value = BookState.Error(result.error)
                }
            }
        }
    }

    fun addToFavorites(book: Book) {
        viewModelScope.launch {
            addToMyFavorites.invoke(book)
        }
    }


    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            networkInfo.isConnected
        }
    }

}
