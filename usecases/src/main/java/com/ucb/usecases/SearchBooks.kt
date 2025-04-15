package com.ucb.usecases

import com.ucb.domain.Book

class SearchBooks {
    fun invoke(toSearch: String): List<Book> {
        return listOf(
            Book(listOf("neni"), "el denil", "2025"),
            Book(listOf("boris", "redo", "guichi'"), "los reales", "2024")
        )
    }
}