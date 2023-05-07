package com.example.animequotes.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animequotes.R
import com.example.animequotes.data.model.Quote

class QuoteAdapter(private val quotes: List<Quote>) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animeTextView: TextView = itemView.findViewById(R.id.animeTextView)
        val characterTextView: TextView = itemView.findViewById(R.id.characterTextView)
        val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.animeTextView.text = quote.anime
        holder.characterTextView.text = quote.character
        holder.quoteTextView.text = quote.quote
    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}
