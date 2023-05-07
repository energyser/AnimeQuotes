package com.example.animequotes.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.data.cloud.RetrofitInstance
import com.example.animequotes.data.repository.Repository
import com.example.animequotes.data.repository.RepositoryImpl
import com.example.animequotes.presentation.model.QuoteResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository: Repository = RepositoryImpl(RetrofitInstance.retrofit)

    fun getRandomQuotes(onComplete: suspend (QuoteResult) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            onComplete.invoke(repository.getTenRandomAnimeQuotes())
        }
    }

    fun getQuotesByTitle(title: String, onComplete: suspend (QuoteResult) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            onComplete.invoke(repository.getTenRandomQuoteByAnimeTitle(title))
        }
    }

    fun getQuotesByCharacter(character: String, onComplete: suspend (QuoteResult) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            onComplete.invoke(repository.getTenRandomQuoteByAnimeCharacter(character))
        }
    }

}