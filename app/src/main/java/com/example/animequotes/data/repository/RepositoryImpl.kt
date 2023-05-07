package com.example.animequotes.data.repository

import com.example.animequotes.data.cloud.AnimeApi
import com.example.animequotes.data.model.Quote
import com.example.animequotes.presentation.model.QuoteResult
import retrofit2.HttpException
import java.net.UnknownHostException

class RepositoryImpl(
    private val animeApi: AnimeApi
) : Repository {

    override suspend fun getTenRandomAnimeQuotes(): QuoteResult {
        return handleApiCall { animeApi.getTenRandomAnimeQuotes() }
    }

    override suspend fun getTenRandomQuoteByAnimeCharacter(character: String): QuoteResult {
        return handleApiCall { animeApi.getTenRandomQuoteByAnimeCharacter(character) }
    }

    override suspend fun getTenRandomQuoteByAnimeTitle(title: String): QuoteResult {
        return handleApiCall { animeApi.getTenRandomQuoteByAnimeTitle(title) }
    }

    private suspend fun handleApiCall(apiCallBlock: suspend () -> List<Quote>): QuoteResult {
        return try {
            QuoteResult.Success(apiCallBlock.invoke())
        } catch (e: Exception) {
            when {
                e is UnknownHostException -> QuoteResult.Error("No Internet connection!")
                e is HttpException && e.code() == 404 -> QuoteResult.Error("No related quotes found!")
                else -> QuoteResult.Error(e.message.toString())
            }
        }
    }
}