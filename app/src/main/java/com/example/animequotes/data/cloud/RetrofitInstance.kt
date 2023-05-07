package com.example.animequotes.data.cloud

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().build()

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: AnimeApi = Retrofit.Builder()
        .baseUrl("https://animechan.vercel.app/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
        .create(AnimeApi::class.java)

}