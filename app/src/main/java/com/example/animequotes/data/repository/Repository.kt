package com.example.animequotes.data.repository

import com.example.animequotes.presentation.model.QuoteResult

interface Repository {

    suspend fun getTenRandomAnimeQuotes(): QuoteResult

    suspend fun getTenRandomQuoteByAnimeCharacter(character: String): QuoteResult

    suspend fun getTenRandomQuoteByAnimeTitle(title: String): QuoteResult

}