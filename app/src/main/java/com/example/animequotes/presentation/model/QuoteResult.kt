package com.example.animequotes.presentation.model

import android.view.View
import com.example.animequotes.data.model.Quote
import com.example.animequotes.databinding.FragmentDetailsBinding
import com.example.animequotes.presentation.details.QuoteAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class QuoteResult {

    abstract suspend fun display(binding: FragmentDetailsBinding)

    class Success(
        private val quotes: List<Quote>
    ) : QuoteResult() {

        override suspend fun display(binding: FragmentDetailsBinding) {
            withContext(Dispatchers.Main) {
                val adapter = QuoteAdapter(quotes)
                binding.quoteRecyclerView.adapter = adapter
                binding.progressBar.visibility = View.GONE
            }
        }

    }

    class Error(private val errorMessage: String) : QuoteResult() {

        override suspend fun display(binding: FragmentDetailsBinding) {
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.GONE
                binding.quoteRecyclerView.visibility = View.GONE
                binding.pressBackTextView.visibility = View.VISIBLE
                binding.errorMessageTextView.visibility = View.VISIBLE

                binding.errorMessageTextView.text = errorMessage
            }
        }

    }

}