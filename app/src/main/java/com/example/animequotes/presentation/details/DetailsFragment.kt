package com.example.animequotes.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.databinding.FragmentDetailsBinding
import com.example.animequotes.presentation.model.QuoteType


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            binding.quoteRecyclerView.context,
            layoutManager.orientation
        )
        binding.quoteRecyclerView.addItemDecoration(dividerItemDecoration)
        binding.quoteRecyclerView.layoutManager = layoutManager

        val viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]

        when (args.quoteType) {
            QuoteType.RANDOM.type -> {
                viewModel.getRandomQuotes {
                    it.display(binding)
                }
            }
            QuoteType.BY_TITLE.type -> {
                viewModel.getQuotesByTitle(args.keyWord) {
                    it.display(binding)
                }
            }
            QuoteType.BY_CHARACTER.type -> {
                viewModel.getQuotesByCharacter(args.keyWord) {
                    it.display(binding)
                }
            }
        }

    }
}