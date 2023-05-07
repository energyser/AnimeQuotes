package com.example.animequotes.data.cloud

import com.example.animequotes.data.model.Quote
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimeApi {

    @Headers("Content-Type: application/json")
    @GET("quotes")
    suspend fun getTenRandomAnimeQuotes(): List<Quote>

    @Headers("Content-Type: application/json")
    @GET("quotes/character")
    suspend fun getTenRandomQuoteByAnimeCharacter(@Query("name") character: String): List<Quote>

    @Headers("Content-Type: application/json")
    @GET("quotes/anime")
    suspend fun getTenRandomQuoteByAnimeTitle(@Query("title") title: String): List<Quote>

}